package com.picpay.transaction_processing.domain.entities;

import com.picpay.shared.domain.ids.AccountId;
import com.picpay.shared.domain.enums.AccountType;
import com.picpay.shared.domain.value_objects.Money;
import com.picpay.transaction_processing.domain.events.FundReceived;
import com.picpay.transaction_processing.domain.exceptions.InsufficientBalanceException;
import com.picpay.transaction_processing.domain.exceptions.RetailerCannotTransferFundsException;
import com.picpay.transaction_processing.domain.ports.TransactionAuthorizerPort;
import jakarta.persistence.*;
import org.springframework.data.domain.AbstractAggregateRoot;

import java.util.Currency;
import java.util.Objects;

@Entity
@Table(name = "accounts")
public class Account extends AbstractAggregateRoot<Account> {
    @EmbeddedId
    protected AccountId id;

    protected AccountType type;

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "amount", column = @Column(name = "initial_balance_amount")),
        @AttributeOverride(name = "currency", column = @Column(name = "initial_balance_currency"))
    })
    protected Money initialBalance;

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "amount", column = @Column(name = "balance_amount")),
        @AttributeOverride(name = "currency", column = @Column(name = "balance_currency"))
    })
    protected Money balance;

    Account() {}

    public Account(
        AccountId id,
        AccountType type,
        Money initialBalance
    ) {
        Objects.requireNonNull(id, "id must not be null");
        this.id = id;

        Objects.requireNonNull(type, "type must not be null");
        this.type = type;

        Objects.requireNonNull(initialBalance, "initialBalance must not be null");
        this.initialBalance = initialBalance;
        this.balance = initialBalance;
    }

    public AccountId id() {
        return id;
    }

    public AccountType type() {
        return type;
    }

    public Currency currency() {
        return balance.currency();
    }

    public void credit(TransactionAuthorizerPort authorizer, Money value) {
        authorizer.authorize();
        balance = balance.add(value);
        registerEvent(new FundReceived(this.id, value));
    }

    public void debit(TransactionAuthorizerPort authorizer, Money value) {
        if (type == AccountType.RETAILER) {
            throw new RetailerCannotTransferFundsException();
        }

        if (!balance.greaterThanOrEqual(value)) {
            throw new InsufficientBalanceException();
        }

        authorizer.authorize();
        balance = balance.subtract(value);
    }

}

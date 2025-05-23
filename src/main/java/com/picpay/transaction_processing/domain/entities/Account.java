package com.picpay.transaction_processing.domain.entities;

import com.picpay.shared.domain.entities.AccountId;
import com.picpay.shared.domain.value_objects.Money;
import com.picpay.transaction_processing.domain.events.BalanceChanged;
import com.picpay.transaction_processing.domain.ports.CreditTransactionAuthorizerPort;
import com.picpay.transaction_processing.domain.ports.DebitTransactionAuthorizerPort;
import jakarta.persistence.*;
import org.springframework.data.domain.AbstractAggregateRoot;

import java.util.Objects;

@Entity(name = "TransactionAccount")
@Table(name = "accounts", schema = "transaction_processing")
public class Account extends AbstractAggregateRoot<Account> {
    @EmbeddedId
    protected AccountId id;

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
        Money initialBalance
    ) {
        Objects.requireNonNull(id, "id must not be null");
        this.id = id;

        Objects.requireNonNull(initialBalance, "initialBalance must not be null");
        this.initialBalance = initialBalance;
        this.balance = initialBalance;
    }

    public AccountId id() {
        return id;
    }

    public Money balance() {
        return balance;
    }

    public void credit(CreditTransactionAuthorizerPort authorizer, Money value) {
        authorizer.authorize();
        balance = balance.add(value);
        this.registerEvent(new BalanceChanged(this.id, balance));
    }

    public void debit(DebitTransactionAuthorizerPort authorizer, Money value) {
        authorizer.authorize(this, value);
        balance = balance.subtract(value);
        this.registerEvent(new BalanceChanged(this.id, balance));
    }

}

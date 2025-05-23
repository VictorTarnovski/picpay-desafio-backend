package com.picpay.account_management.domain.entities;

import com.picpay.account_management.domain.repositories.AccountRepository;
import com.picpay.shared.domain.entities.AccountId;
import com.picpay.shared.domain.enums.AccountType;
import com.picpay.account_management.domain.events.AccountCreated;
import com.picpay.auth.domain.entities.UserId;
import jakarta.persistence.*;
import org.springframework.data.domain.AbstractAggregateRoot;

import java.util.Currency;
import java.util.Objects;

@Entity(name = "ManagementAccount")
@Table(name = "accounts", schema = "account_management")
public class Account extends AbstractAggregateRoot<Account> {
    @EmbeddedId
    protected AccountId id;

    protected AccountType type;

    protected Currency currency;

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "user_id"))
    })
    protected UserId userId;

    Account() {}

    public Account(AccountRepository repository, AccountType type, Currency currency, UserId userId) {
        Objects.requireNonNull(repository, "repository must not be null");
        this.id = repository.nextId();

        Objects.requireNonNull(type, "type must not be null");
        this.type = type;

        Objects.requireNonNull(currency, "currency must not be null");
        this.currency = currency;

        Objects.requireNonNull(type, "userId must not be null");
        this.userId = userId;

        this.registerEvent(new AccountCreated(this.id, this.currency));
    }

    AccountId id() {
        return id;
    }
}

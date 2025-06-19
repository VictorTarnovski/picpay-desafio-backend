package com.picpay.account_management.domain.entities;

import com.picpay.account_management.domain.repositories.AccountRepository;
import com.picpay.shared.domain.ids.AccountId;
import com.picpay.shared.domain.ids.UserId;
import com.picpay.shared.domain.enums.AccountType;
import com.picpay.shared.domain.events.AccountOpened;
import jakarta.persistence.*;
import org.springframework.data.domain.AbstractAggregateRoot;

import java.util.Currency;
import java.util.Objects;

@Entity
@Table(name = "accounts", schema = "account_management")
public class Account extends AbstractAggregateRoot<Account> {
    @EmbeddedId
    protected AccountId id;

    protected Currency currency;

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "user_id"))
    })
    protected UserId userId;

    Account() {}

    public Account(
        AccountRepository repository,
        AccountType type,
        Currency currency,
        UserId userId
    ) {
        Objects.requireNonNull(repository, "repository must not be null");
        this.id = repository.nextId();

        Objects.requireNonNull(type, "type must not be null");

        Objects.requireNonNull(currency, "currency must not be null");
        this.currency = currency;

        Objects.requireNonNull(userId, "userId must not be null");
        this.userId = userId;

        this.registerEvent(new AccountOpened(this.id, type, this.currency));
    }

    public AccountId id() {
        return id;
    }
}

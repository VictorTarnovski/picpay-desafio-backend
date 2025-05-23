package com.picpay.transaction_processing.domain.entities;

import com.picpay.shared.domain.entities.AccountId;
import com.picpay.shared.domain.value_objects.Money;
import com.picpay.transaction_processing.domain.events.TransactionCreated;
import com.picpay.transaction_processing.domain.repositories.TransactionRepository;
import jakarta.persistence.*;
import org.springframework.data.domain.AbstractAggregateRoot;

import java.util.Objects;

@Entity
@Table(name = "transactions", schema = "transaction_processing")
public class Transaction extends AbstractAggregateRoot<Transaction> {
    @EmbeddedId
    protected TransactionId id;

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "amount", column = @Column(name = "value_amount")),
        @AttributeOverride(name = "currency", column = @Column(name = "value_currency"))
    })
    protected Money value;

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "payer_id"))
    })
    protected AccountId payerId;

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "payee_id"))
    })
    protected AccountId payeeId;

    Transaction() {}

    public Transaction(TransactionRepository repository, Money value, AccountId payerId, AccountId payeeId) {
        Objects.requireNonNull(repository, "repository must not be null");
        this.id = repository.nextId();

        Objects.requireNonNull(value, "value must not be null");
        this.value = value;

        Objects.requireNonNull(payerId, "payerId must not be null");
        this.payerId = payerId;

        Objects.requireNonNull(payeeId, "payeeId must not be null");
        this.payeeId = payeeId;

        this.registerEvent(new TransactionCreated(this.id, this.value, this.payerId, this.payeeId));
    }

    public Money value() {
        return value;
    }
}

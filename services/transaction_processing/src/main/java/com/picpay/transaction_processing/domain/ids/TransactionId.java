package com.picpay.transaction_processing.domain.ids;

import jakarta.persistence.Embeddable;

import java.util.Objects;

@Embeddable
public record TransactionId(Long id) {
    public TransactionId {
        Objects.requireNonNull(id, "id must not be null");
    }

    @Override
    public String toString() {
        return id.toString();
    }
}

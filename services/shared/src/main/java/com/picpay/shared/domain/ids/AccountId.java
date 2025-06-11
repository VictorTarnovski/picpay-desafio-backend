package com.picpay.shared.domain.ids;

import jakarta.persistence.Embeddable;

import java.util.Objects;

@Embeddable
public record AccountId(Long id) {
    public AccountId {
        Objects.requireNonNull(id, "id must not be null");
    }

    @Override
    public String toString() {
        return id.toString();
    }
}

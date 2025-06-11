package com.picpay.shared.domain.ids;

import jakarta.persistence.Embeddable;

import java.util.Objects;

@Embeddable
public record UserId(Long id) {
    public UserId {
        Objects.requireNonNull(id, "id must not be null");
    }

    @Override
    public String toString() {
        return id.toString();
    }
}

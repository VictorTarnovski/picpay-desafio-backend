package com.picpay.shared.domain.value_objects;

import jakarta.persistence.Embeddable;

import java.util.Currency;
import java.util.Objects;

@Embeddable
public record Money(long amount, Currency currency) {
    public Money {
        Objects.requireNonNull(currency, "currency must not be null");
    }

    public static Money Real(long amount) {
        return new Money(amount, Currency.getInstance("BRL"));
    }

    public long amount() {
        return amount;
    }

    public Money add(Money other) {
        checkCurrencyMatch(other);
        return new Money(this.amount + other.amount, this.currency);
    }

    public Money subtract(Money other) {
        checkCurrencyMatch(other);
        return new Money(this.amount - other.amount, this.currency);
    }

    private boolean greaterThan(Money other) {
        checkCurrencyMatch(other);
        return this.amount > other.amount;
    }

    public boolean greaterThanOrEqual(Money other) {
        return greaterThan(other) || this.equals(other);
    }

    private void checkCurrencyMatch(Money other) {
        if (!this.currency.equals(other.currency)) {
            throw new IllegalArgumentException("Currencies must match");
        }
    }
}

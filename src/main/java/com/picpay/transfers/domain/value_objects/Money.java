package com.picpay.transfers.domain.value_objects;

import java.util.Currency;
import java.util.Objects;

public record Money(int amount, Currency currency) {
    public Money {
        Objects.requireNonNull(currency, "Currency cannot be null");
    }

    public int amount() {
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

    private void checkCurrencyMatch(Money other) {
        if (!this.currency.equals(other.currency)) {
            throw new IllegalArgumentException("Currencies must match");
        }
    }
}

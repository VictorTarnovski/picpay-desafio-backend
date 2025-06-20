package com.picpay.shared.domain.events;

import java.util.Currency;
import java.util.Objects;

import com.picpay.shared.domain.ids.AccountId;
import com.picpay.shared.domain.enums.AccountType;

public record AccountOpened(AccountId id, AccountType type, Currency currency) {
    public AccountOpened {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(type, "type must not be null");
        Objects.requireNonNull(currency, "currency must not be null");
    }
}

package com.picpay.shared.domain.events;

import com.picpay.shared.domain.ids.UserId;

import java.util.Objects;

import com.picpay.shared.domain.enums.AccountType;

public record UserRegistered(UserId id, AccountType accountType) {
    public UserRegistered {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(accountType, "accountType must not be null");
    }
}

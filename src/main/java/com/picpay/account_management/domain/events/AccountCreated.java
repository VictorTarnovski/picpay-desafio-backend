package com.picpay.account_management.domain.events;

import com.picpay.shared.domain.entities.AccountId;

public record AccountCreated(AccountId id) {
}

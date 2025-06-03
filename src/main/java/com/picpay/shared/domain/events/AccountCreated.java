package com.picpay.shared.domain.events;

import com.picpay.shared.domain.entities.AccountId;

import java.util.Currency;

public record AccountCreated(AccountId id, Currency currency) {
}

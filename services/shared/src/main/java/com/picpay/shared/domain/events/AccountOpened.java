package com.picpay.shared.domain.events;

import java.util.Currency;

import com.picpay.shared.domain.ids.AccountId;
import com.picpay.shared.domain.enums.AccountType;

public record AccountOpened(AccountId id, AccountType type, Currency currency) {
}

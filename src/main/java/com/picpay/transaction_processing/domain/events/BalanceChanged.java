package com.picpay.transaction_processing.domain.events;

import com.picpay.shared.domain.entities.AccountId;
import com.picpay.shared.domain.value_objects.Money;

public record BalanceChanged(AccountId accountId, Money balance) {
}

package com.picpay.transaction_processing.domain.events;

import com.picpay.shared.domain.ids.AccountId;
import com.picpay.shared.domain.value_objects.Money;

public record BalanceChanged(AccountId accountId, Money balance) {
}

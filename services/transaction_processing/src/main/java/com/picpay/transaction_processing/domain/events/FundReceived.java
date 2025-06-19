package com.picpay.transaction_processing.domain.events;

import java.util.Objects;

import com.picpay.shared.domain.ids.AccountId;
import com.picpay.shared.domain.value_objects.Money;

public record FundReceived(AccountId accountId, Money value) {
    public FundReceived {
        Objects.requireNonNull(accountId, "accountId must not be null");
        Objects.requireNonNull(value, "value must not be null");
    }
}

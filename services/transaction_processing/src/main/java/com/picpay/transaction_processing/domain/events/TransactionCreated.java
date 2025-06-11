package com.picpay.transaction_processing.domain.events;

import com.picpay.shared.domain.ids.AccountId;
import com.picpay.shared.domain.value_objects.Money;
import com.picpay.transaction_processing.domain.entities.TransactionId;

public record TransactionCreated(TransactionId id, Money value, AccountId payerId, AccountId payeeId) {
}

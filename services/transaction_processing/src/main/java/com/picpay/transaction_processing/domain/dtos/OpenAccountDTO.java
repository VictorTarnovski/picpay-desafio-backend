package com.picpay.transaction_processing.domain.dtos;

import java.util.Currency;
import java.util.Objects;

import com.picpay.shared.domain.enums.AccountType;
import com.picpay.shared.domain.ids.AccountId;

public record OpenAccountDTO(AccountId id, AccountType type, Currency currency) {
    public OpenAccountDTO {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(type, "type must not be null");
        Objects.requireNonNull(currency, "currency must not be null");
    }
}

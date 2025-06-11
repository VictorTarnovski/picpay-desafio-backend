package com.picpay.account_management.domain.dtos;

import com.picpay.shared.domain.enums.AccountType;

public record CreateAccountDTO(AccountType type, String currencyCountryCode) {
}

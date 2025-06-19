package com.picpay.account_management.domain.dtos;

import com.picpay.shared.domain.ids.UserId;
import com.picpay.shared.domain.enums.AccountType;

public record OpenAccountDTO(AccountType type, UserId userId) {
}

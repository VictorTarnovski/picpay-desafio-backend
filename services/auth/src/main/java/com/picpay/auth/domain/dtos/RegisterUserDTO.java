package com.picpay.auth.domain.dtos;

import com.picpay.shared.domain.enums.AccountType;

public record RegisterUserDTO(String fullName, String document, String email, AccountType accountType) {
}

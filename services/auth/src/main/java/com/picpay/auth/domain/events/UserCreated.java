package com.picpay.auth.domain.events;

import com.picpay.auth.domain.ids.UserId;
import com.picpay.shared.domain.enums.AccountType;

public record UserCreated(UserId id, AccountType type) {
}

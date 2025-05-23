package com.picpay.auth.domain.events;

import com.picpay.auth.domain.enums.UserType;
import com.picpay.auth.domain.entities.UserId;

public record UserCreated(UserId id, UserType type) {
}

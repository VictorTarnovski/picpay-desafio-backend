package com.picpay.transfers.domain.value_objects;

import com.picpay.transfers.domain.exceptions.InvalidEmailFormatException;

import java.util.Objects;
import java.util.regex.Pattern;

public record Email(String value) {

    private static final Pattern EMAIL_PATTERN = Pattern.compile(
        "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,}$",
        Pattern.CASE_INSENSITIVE
    );

    public Email {
        Objects.requireNonNull(value, "CPF must not be null");
        if (!EMAIL_PATTERN.matcher(value).matches()) {
            throw new InvalidEmailFormatException(value);
        }
    }
}

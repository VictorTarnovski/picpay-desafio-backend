package com.picpay.auth.domain.value_objects;

import com.picpay.auth.domain.exceptions.InvalidEmailFormatException;
import jakarta.persistence.Embeddable;

import java.util.Objects;
import java.util.regex.Pattern;

@Embeddable
public record Email(String email) {

    private static final Pattern EMAIL_PATTERN = Pattern.compile(
        "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,}$",
        Pattern.CASE_INSENSITIVE
    );

    public Email {
        Objects.requireNonNull(email, "CPF must not be null");
        if (!EMAIL_PATTERN.matcher(email).matches()) {
            throw new InvalidEmailFormatException(email);
        }
    }
}

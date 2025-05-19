package com.picpay.transfers.domain.value_objects;

import java.util.Objects;
import java.util.regex.Pattern;

public record CPF(String value) {
    private static final Pattern ONLY_DIGITS = Pattern.compile("\\d{11}");

    public CPF(String value) {
        Objects.requireNonNull(value, "CPF must not be null");
        String numeric = value.replaceAll("\\D", "");

        if (!ONLY_DIGITS.matcher(numeric).matches()) {
            throw new IllegalArgumentException("CPF must contain exactly 11 digits");
        }

        if (!isValid(numeric)) {
            throw new IllegalArgumentException("Invalid CPF");
        }

        this.value = this.format(numeric);
    }

    private boolean isValid(String cpf) {
        // Invalid known CPFs
        if (cpf.chars().distinct().count() == 1) return false;

        // Validate first check digit
        int firstCheck = calculateCheckDigit(cpf, 9);
        int secondCheck = calculateCheckDigit(cpf, 10);

        return cpf.charAt(9) - '0' == firstCheck && cpf.charAt(10) - '0' == secondCheck;
    }

    private int calculateCheckDigit(String cpf, int length) {
        int sum = 0;
        for (int i = 0; i < length; i++) {
            sum += (cpf.charAt(i) - '0') * (length + 1 - i);
        }
        int remainder = sum % 11;
        return remainder < 2 ? 0 : 11 - remainder;
    }

    private String format(String value) {
        return value.substring(0, 3) + "." +
            value.substring(3, 6) + "." +
            value.substring(6, 9) + "-" +
            value.substring(9);
    }
}

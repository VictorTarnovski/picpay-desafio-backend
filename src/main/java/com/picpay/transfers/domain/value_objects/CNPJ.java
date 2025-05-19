package com.picpay.transfers.domain.value_objects;

import java.util.Objects;
import java.util.regex.Pattern;

public record CNPJ(String value) {
    private static final Pattern ONLY_DIGITS = Pattern.compile("\\d{14}");

    public CNPJ(String value) {
        Objects.requireNonNull(value, "CNPJ must not be null");

        String numeric = value.replaceAll("\\D", "");

        if (!ONLY_DIGITS.matcher(numeric).matches()) {
            throw new IllegalArgumentException("CNPJ must contain exactly 14 digits");
        }

        if (!isValid(numeric)) {
            throw new IllegalArgumentException("Invalid CNPJ");
        }

        this.value = format(numeric);
    }

    private boolean isValid(String cnpj) {
        // Repeated digits check
        if (cnpj.chars().distinct().count() == 1) return false;

        int firstCheck = calculateCheckDigit(cnpj, new int[]{5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2});
        int secondCheck = calculateCheckDigit(cnpj, new int[]{6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2});

        return cnpj.charAt(12) - '0' == firstCheck && cnpj.charAt(13) - '0' == secondCheck;
    }

    private int calculateCheckDigit(String cnpj, int[] weights) {
        int sum = 0;
        for (int i = 0; i < weights.length; i++) {
            sum += (cnpj.charAt(i) - '0') * weights[i];
        }
        int remainder = sum % 11;
        return remainder < 2 ? 0 : 11 - remainder;
    }

    private String format(String value) {
        return value.substring(0, 2) + "." +
            value.substring(2, 5) + "." +
            value.substring(5, 8) + "/" +
            value.substring(8, 12) + "-" +
            value.substring(12);
    }
}

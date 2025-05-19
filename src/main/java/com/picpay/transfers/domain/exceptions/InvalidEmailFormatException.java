package com.picpay.transfers.domain.exceptions;

public class InvalidEmailFormatException extends RuntimeException {
    public InvalidEmailFormatException(String value) {
        super("Invalid email format: " + value);
    }
}

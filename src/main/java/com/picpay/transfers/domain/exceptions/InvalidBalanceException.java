package com.picpay.transfers.domain.exceptions;

public class InvalidBalanceException extends RuntimeException {
    public InvalidBalanceException() {
        super("Invalid balance");
    }
}

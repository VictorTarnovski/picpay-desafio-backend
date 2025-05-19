package com.picpay.transfers.domain.exceptions;

public class InvalidCPFException extends RuntimeException {
    public InvalidCPFException() {
        super("Invalid CPF");
    }
}

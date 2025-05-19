package com.picpay.transfers.domain.exceptions;

public class InvalidCNPJLenghtException extends RuntimeException {
    public InvalidCNPJLenghtException() {
        super("CNPJ must contain exactly 14 digits");
    }
}

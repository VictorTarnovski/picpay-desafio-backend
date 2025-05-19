package com.picpay.transfers.domain.exceptions;

public class InvalidCPFLenghtException extends RuntimeException {
    public InvalidCPFLenghtException() {
        super("CPF must contain exactly 11 digits");
    }
}

package com.picpay.transfers.domain.exceptions;

public class InvalidCNPJException extends RuntimeException {
    public InvalidCNPJException() {
        super("Invalid CNPJ");
    }
}

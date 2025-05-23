package com.picpay.auth.domain.exceptions;

import com.picpay.shared.domain.exceptions.PicPayException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class InvalidCNPJLenghtException extends PicPayException {
    public InvalidCNPJLenghtException() {
        super("CNPJ must contain exactly 14 digits");
    }

    @Override
    public ProblemDetail toProblemDetail() {
        var pb = super.toProblemDetail();

        pb.setStatus(HttpStatus.UNPROCESSABLE_ENTITY);
        pb.setTitle("Invalid CNPJ");
        pb.setDetail("CNPJ must contain exactly 14 digits");

        return pb;
    }
}

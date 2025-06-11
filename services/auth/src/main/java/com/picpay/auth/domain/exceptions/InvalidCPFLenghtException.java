package com.picpay.auth.domain.exceptions;

import com.picpay.shared.domain.exceptions.PicPayException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class InvalidCPFLenghtException extends PicPayException {
    public InvalidCPFLenghtException() {
        super("CPF must contain exactly 11 digits");
    }

    @Override
    public ProblemDetail toProblemDetail() {
        var pb = super.toProblemDetail();

        pb.setStatus(HttpStatus.UNPROCESSABLE_ENTITY);
        pb.setTitle("Invalid CPF");
        pb.setDetail("CPF must contain exactly 11 digits");

        return pb;
    }
}

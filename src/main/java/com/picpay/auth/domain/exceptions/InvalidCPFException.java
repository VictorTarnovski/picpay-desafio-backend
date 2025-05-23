package com.picpay.auth.domain.exceptions;

import com.picpay.shared.domain.exceptions.PicPayException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class InvalidCPFException extends PicPayException {
    public InvalidCPFException() {
        super("Invalid CPF");
    }

    @Override
    public ProblemDetail toProblemDetail() {
        var pb = super.toProblemDetail();

        pb.setStatus(HttpStatus.UNPROCESSABLE_ENTITY);
        pb.setTitle("Invalid CPF");

        return pb;
    }
}

package com.picpay.auth.domain.exceptions;

import com.picpay.shared.domain.exceptions.PicPayException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class InvalidCNPJException extends PicPayException {
    public InvalidCNPJException() {
        super("Invalid CNPJ");
    }

    @Override
    public ProblemDetail toProblemDetail() {
        var pb = super.toProblemDetail();

        pb.setStatus(HttpStatus.UNPROCESSABLE_ENTITY);
        pb.setTitle("Invalid CNPJ");

        return pb;
    }
}

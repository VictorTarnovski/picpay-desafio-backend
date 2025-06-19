package com.picpay.auth.domain.exceptions;

import com.picpay.shared.domain.exceptions.PicPayException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class BlankPasswordSaltException extends PicPayException {
    public BlankPasswordSaltException() {
        super("Password salt must not be blank");
    }

    @Override
    public ProblemDetail toProblemDetail() {
        var pb = super.toProblemDetail();

        pb.setStatus(HttpStatus.UNPROCESSABLE_ENTITY);
        pb.setTitle("Blank password salt");
        pb.setDetail("Password salt must not be blank");

        return pb;
    }
}

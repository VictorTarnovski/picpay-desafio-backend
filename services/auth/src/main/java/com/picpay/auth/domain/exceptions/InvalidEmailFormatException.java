package com.picpay.auth.domain.exceptions;

import com.picpay.shared.domain.exceptions.PicPayException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class InvalidEmailFormatException extends PicPayException {
    private final String value;

    public InvalidEmailFormatException(String value) {
        super("Invalid Email: " + value);
        this.value = value;
    }

    @Override
    public ProblemDetail toProblemDetail() {
        var pb = super.toProblemDetail();

        pb.setStatus(HttpStatus.UNPROCESSABLE_ENTITY);
        pb.setTitle("Invalid Email format");
        pb.setDetail("Invalid Email format: " + value);

        return pb;
    }
}

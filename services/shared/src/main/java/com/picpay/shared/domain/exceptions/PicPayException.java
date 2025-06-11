package com.picpay.shared.domain.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

import java.time.Instant;
import java.time.ZoneOffset;

public class PicPayException extends RuntimeException {
    public PicPayException(String message) {
        super(message);
    }

    public ProblemDetail toProblemDetail() {
        var pb = ProblemDetail.forStatus(HttpStatus.INTERNAL_SERVER_ERROR);

        pb.setTitle("Internal server error");
        pb.setProperty("timestamp", Instant.now().atOffset(ZoneOffset.UTC));

        return pb;
    }
}

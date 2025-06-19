package com.picpay.transaction_processing.domain.exceptions;

import com.picpay.shared.domain.exceptions.PicPayException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class UnauthorizedTransactionException extends PicPayException {
    public UnauthorizedTransactionException() {
        super("Unauthorized transaction");
    }

    @Override
    public ProblemDetail toProblemDetail() {
        var pb = super.toProblemDetail();

        pb.setStatus(HttpStatus.FORBIDDEN);
        pb.setTitle("Unauthorized transaction");

        return pb;
    }
}

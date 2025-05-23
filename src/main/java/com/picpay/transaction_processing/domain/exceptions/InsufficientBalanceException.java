package com.picpay.transaction_processing.domain.exceptions;

import com.picpay.shared.domain.exceptions.PicPayException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class InsufficientBalanceException extends PicPayException {
    public InsufficientBalanceException() {
        super("Insufficient balance");
    }

    @Override
    public ProblemDetail toProblemDetail() {
        var pb = super.toProblemDetail();

        pb.setStatus(HttpStatus.UNPROCESSABLE_ENTITY);
        pb.setTitle("Insufficient balance.");
        pb.setDetail("Cannot transfer a value bigger than the payer balance");

        return pb;
    }
}

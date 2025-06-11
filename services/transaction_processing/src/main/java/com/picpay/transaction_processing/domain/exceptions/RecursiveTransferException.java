package com.picpay.transaction_processing.domain.exceptions;

import com.picpay.shared.domain.exceptions.PicPayException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class RecursiveTransferException extends PicPayException {
    public RecursiveTransferException() {
        super("Cannot operate a recursive transfer");
    }

    @Override
    public ProblemDetail toProblemDetail() {
        var pb = super.toProblemDetail();

        pb.setStatus(HttpStatus.UNPROCESSABLE_ENTITY);
        pb.setTitle("Cannot operate a recursive transfer");
        pb.setDetail("Payer account and payee account must be different");

        return pb;
    }
}

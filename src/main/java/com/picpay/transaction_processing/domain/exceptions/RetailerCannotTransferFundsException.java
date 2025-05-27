package com.picpay.transaction_processing.domain.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class RetailerCannotTransferFundsException extends UnauthorizedTransactionException {

    @Override
    public ProblemDetail toProblemDetail() {
        var pb = super.toProblemDetail();

        pb.setStatus(HttpStatus.FORBIDDEN);
        pb.setDetail("Retailer cannot transfer funds");

        return pb;
    }
}

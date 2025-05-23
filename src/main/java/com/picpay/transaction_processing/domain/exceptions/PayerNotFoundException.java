package com.picpay.transaction_processing.domain.exceptions;

import com.picpay.shared.domain.entities.AccountId;
import com.picpay.shared.domain.exceptions.PicPayException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class PayerNotFoundException extends PicPayException {
    private final AccountId id;

    public PayerNotFoundException(AccountId id) {
        super("Payer#" + id + " was not found");
        this.id = id;
    }

    @Override
    public ProblemDetail toProblemDetail() {
        var pb = super.toProblemDetail();

        pb.setStatus(HttpStatus.NOT_FOUND);
        pb.setTitle("Payer#" + id + " was not found");

        return pb;
    }
}

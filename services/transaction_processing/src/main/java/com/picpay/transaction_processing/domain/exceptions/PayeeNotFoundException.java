package com.picpay.transaction_processing.domain.exceptions;

import com.picpay.shared.domain.ids.AccountId;
import com.picpay.shared.domain.exceptions.PicPayException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class PayeeNotFoundException extends PicPayException {
    private final AccountId id;

    public PayeeNotFoundException(AccountId id) {
        super("Payee#" + id + " was not found");
        this.id = id;
    }

    @Override
    public ProblemDetail toProblemDetail() {
        var pb = super.toProblemDetail();

        pb.setStatus(HttpStatus.NOT_FOUND);
        pb.setTitle("Payee#" + id + " was not found");

        return pb;
    }
}

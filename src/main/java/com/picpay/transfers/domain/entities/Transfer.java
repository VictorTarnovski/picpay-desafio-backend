package com.picpay.transfers.domain.entities;

import com.picpay.transfers.domain.value_objects.*;

public final class Transfer {
    private Money value;
    private long payerId;
    private long payeeId;

    public Transfer(Money value, long payerId, long payeeId) {
        this.value = value;
        this.payerId = payerId;
        this.payeeId = payeeId;
    }

    public Money value() {
        return value;
    }
}

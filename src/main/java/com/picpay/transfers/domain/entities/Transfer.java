package com.picpay.transfers.domain.entities;

import com.picpay.transfers.domain.value_objects.*;

public final class Transfer {
    private Money value;
    private int payerId;
    private int payeeId;

    public Transfer(Money value, int payerId, int payeeId) {
        this.value = value;
        this.payerId = payerId;
        this.payeeId = payeeId;
    }

    public Money value() {
        return value;
    }
}

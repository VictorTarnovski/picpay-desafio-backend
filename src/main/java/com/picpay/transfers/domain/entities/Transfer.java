package com.picpay.transfers.domain.entities;

import com.picpay.transfers.domain.value_objects.*;

public final class Transfer {
    private Money amount;
    private int payerId;
    private int payeeId;

    public Transfer(Money amount, int payerId, int payeeId) {
        this.amount = amount;
        this.payerId = payerId;
        this.payeeId = payeeId;
    }

    public Money amount() {
        return amount;
    }
}

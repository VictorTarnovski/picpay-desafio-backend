package com.picpay.transfers.domain;

import com.picpay.transfers.domain.value_objects.Money;

public interface TransferPayee {
    int id();
    void receiveTransfer(Money amount, int payerId, int payeeId);
}

package com.picpay.transfers.domain;

import com.picpay.transfers.domain.value_objects.Money;

public interface TransferPayee {
    long id();
    void receiveTransfer(Money amount, long payerId, long payeeId);
}

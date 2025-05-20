package com.picpay.transfers.domain;

import com.picpay.transfers.domain.entities.Transfer;

public interface TransferPayee {
    long id();
    void receiveTransfer(Transfer transfer);
}

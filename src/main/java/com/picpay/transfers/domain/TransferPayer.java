package com.picpay.transfers.domain;

import com.picpay.transfers.domain.entities.Transfer;

public interface TransferPayer {
    void payTransfer(Transfer transfer, TransferPayee payee);
}

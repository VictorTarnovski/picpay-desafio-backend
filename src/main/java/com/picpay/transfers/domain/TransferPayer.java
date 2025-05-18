package com.picpay.transfers.domain;

import com.picpay.transfers.domain.value_objects.Money;

public interface TransferPayer {
    void payTransfer(Money amount, TransferPayee payee);
}

package com.picpay.transaction_processing.domain.ports;

import com.picpay.transaction_processing.domain.entities.Account;
import com.picpay.shared.domain.value_objects.Money;

public interface DebitTransactionAuthorizerPort {
    void authorize(Account account, Money value);
}

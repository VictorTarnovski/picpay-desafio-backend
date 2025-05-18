package com.picpay.transfers.domain.entities;

import com.picpay.transfers.domain.*;
import com.picpay.transfers.domain.value_objects.*;

public final class Customer extends User implements TransferPayer {
    public Customer(
        String fullName,
        CPF cpf,
        Email email,
        UserPasswordProvider provider,
        Money initialBalance
    ) {
        super(fullName, cpf.toString(), email, provider, initialBalance);
    }

    public void payTransfer(Money amount, TransferPayee payee) {
        var newBalance = balance.subtract(amount);
        updateBalance(newBalance);
        addTransfer(amount, id, payee.id());

        payee.receiveTransfer(amount, id, payee.id());
    }
}

package com.picpay.transfers.domain.entities;

import com.picpay.transfers.domain.*;
import com.picpay.transfers.domain.exceptions.InvalidBalanceException;
import com.picpay.transfers.domain.value_objects.*;

public abstract class User implements TransferPayee {
    protected long id;
    protected String fullName;
    protected String document;
    protected Email email;
    protected String password;
    protected Money balance;

    public User(
        String fullName,
        String document,
        Email email,
        UserPasswordProvider provider,
        Money initialBalance
    ) {
        this.fullName = fullName;
        this.document = document;
        this.email = email;
        this.password = provider.provide();
        this.balance = initialBalance;
    }

    public long id() {
        return id;
    }

    public void updateBalance(Money newBalance) {
        if (newBalance.amount() < 0) {
            throw new InvalidBalanceException();
        }
    }

    public void receiveTransfer(Transfer transfer) {
        var newBalance = balance.add(transfer.value());
        updateBalance(newBalance);
    }
}

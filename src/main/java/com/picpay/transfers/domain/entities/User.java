package com.picpay.transfers.domain.entities;

import com.picpay.transfers.domain.*;
import com.picpay.transfers.domain.exceptions.InvalidBalanceException;
import com.picpay.transfers.domain.value_objects.*;

import java.util.List;

public abstract class User implements TransferPayee {
    protected int id;
    protected String fullName;
    protected String document;
    protected Email email;
    protected String password;
    protected Money balance;
    protected List<Transfer> transfers;

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

    public int id() {
        return id;
    }

    public void updateBalance(Money newBalance) {
        if (newBalance.amount() < 0) {
            throw new InvalidBalanceException();
        }
    }

    protected void addTransfer(Money amount, int payerId, int payeeId) {
        var transfer = new Transfer(amount, payerId, payeeId);
        this.transfers.add(transfer);
    }

    public void receiveTransfer(Money amount, int payerId, int payeeId) {
        var newBalance = balance.add(amount);
        updateBalance(newBalance);
        addTransfer(amount, payerId, payeeId);
    }
}

package com.picpay.transaction_processing.application.adapters;

import com.picpay.transaction_processing.domain.entities.Account;
import com.picpay.transaction_processing.domain.exceptions.InsufficientBalanceException;
import com.picpay.transaction_processing.domain.ports.DebitTransactionAuthorizerPort;
import com.picpay.shared.domain.value_objects.Money;
import org.springframework.stereotype.Component;

@Component
public class DebitTransactionAuthorizerAdapter implements DebitTransactionAuthorizerPort {
    private final TransactionAuthorizerBase authorizer;

    public DebitTransactionAuthorizerAdapter(TransactionAuthorizerBase authorizer) {
        this.authorizer = authorizer;
    }

    @Override
    public void authorize(Account account, Money value) {
        if (!account.isBalanceGreaterThanOrEqual(value)) {
            throw new InsufficientBalanceException();
        }
        authorizer.authorize();

    }
}

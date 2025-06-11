package com.picpay.transaction_processing.application.adapters;

import com.picpay.transaction_processing.domain.ports.CreditTransactionAuthorizerPort;
import org.springframework.stereotype.Component;

@Component
public class CreditTransactionAuthorizerAdapter implements CreditTransactionAuthorizerPort {
    private final TransactionAuthorizerBase authorizer;

    public CreditTransactionAuthorizerAdapter(TransactionAuthorizerBase authorizer) {
        this.authorizer = authorizer;
    }

    @Override
    public void authorize() {
        authorizer.authorize();
    }
}

package com.picpay.transaction_processing.application.adapters;

import com.picpay.account_management.domain.ports.AccountManagementModulePort;
import com.picpay.shared.domain.enums.AccountType;
import com.picpay.transaction_processing.domain.entities.Account;
import com.picpay.transaction_processing.domain.exceptions.InsufficientBalanceException;
import com.picpay.transaction_processing.domain.exceptions.RetailerCannotTransferFundsException;
import com.picpay.transaction_processing.domain.ports.DebitTransactionAuthorizerPort;
import com.picpay.shared.domain.value_objects.Money;
import org.springframework.stereotype.Component;

@Component
public class DebitTransactionAuthorizerAdapter implements DebitTransactionAuthorizerPort {
    private final TransactionAuthorizerBase authorizer;
    private final AccountManagementModulePort accountManagement;

    public DebitTransactionAuthorizerAdapter(
        TransactionAuthorizerBase authorizer,
        AccountManagementModulePort accountManagement) {
        this.authorizer = authorizer;
        this.accountManagement = accountManagement;
    }

    @Override
    public void authorize(Account account, Money value) {
        if (accountManagement.type(account.id()).get() == AccountType.RETAILER) {
            throw new RetailerCannotTransferFundsException();
        }

        if (!account.isBalanceGreaterThanOrEqual(value)) {
            throw new InsufficientBalanceException();
        }

        authorizer.authorize();
    }
}

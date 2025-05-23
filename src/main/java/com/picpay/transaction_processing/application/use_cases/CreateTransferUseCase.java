package com.picpay.transaction_processing.application.use_cases;

import com.picpay.shared.domain.entities.AccountId;
import com.picpay.transaction_processing.domain.dtos.CreateTransferDto;
import com.picpay.transaction_processing.domain.entities.Transaction;
import com.picpay.transaction_processing.domain.entities.TransactionId;
import com.picpay.transaction_processing.domain.exceptions.PayeeNotFoundException;
import com.picpay.transaction_processing.domain.exceptions.PayerNotFoundException;
import com.picpay.transaction_processing.domain.ports.CreditTransactionAuthorizerPort;
import com.picpay.transaction_processing.domain.ports.DebitTransactionAuthorizerPort;
import com.picpay.transaction_processing.domain.repositories.AccountRepository;
import com.picpay.transaction_processing.domain.repositories.TransactionRepository;
import com.picpay.shared.domain.value_objects.Money;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class CreateTransferUseCase {
    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;
    private final DebitTransactionAuthorizerPort debitTransactionAuthorizer;
    private final CreditTransactionAuthorizerPort creditTransactionAuthorizer;

    public CreateTransferUseCase(
        AccountRepository accountRepository,
        TransactionRepository transactionRepository,
        DebitTransactionAuthorizerPort debitTransactionAuthorizer,
        CreditTransactionAuthorizerPort creditTransactionAuthorizer
    ) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
        this.debitTransactionAuthorizer = debitTransactionAuthorizer;
        this.creditTransactionAuthorizer = creditTransactionAuthorizer;
    }

    public void execute(CreateTransferDto dto) {
        var payerId = new AccountId(dto.payer());
        var payer = accountRepository
                .findById(payerId)
                .orElseThrow(() -> new PayerNotFoundException(payerId));
        var payeeId = new AccountId(dto.payee());
        var payee = accountRepository
                .findById(new AccountId(dto.payee()))
                .orElseThrow(() -> new PayeeNotFoundException(payeeId));
        var amount = new Money(dto.value(), payer.balance().currency());

        var transaction = new Transaction(
            transactionRepository,
            amount,
            payer.id(),
            payee.id()
        );

        payer.debit(debitTransactionAuthorizer, amount);
        payee.credit(creditTransactionAuthorizer, amount);

        accountRepository.save(payer);
        accountRepository.save(payee);
        transactionRepository.save(transaction);
    }
}

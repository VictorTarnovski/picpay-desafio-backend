package com.picpay.transaction_processing.application.use_cases;

import com.picpay.shared.domain.ids.AccountId;
import com.picpay.transaction_processing.domain.dtos.CreateTransferDTO;
import com.picpay.transaction_processing.domain.entities.Transaction;
import com.picpay.transaction_processing.domain.exceptions.PayeeNotFoundException;
import com.picpay.transaction_processing.domain.exceptions.PayerNotFoundException;
import com.picpay.transaction_processing.domain.exceptions.RecursiveTransferException;
import com.picpay.transaction_processing.domain.ports.TransactionAuthorizerPort;
import com.picpay.transaction_processing.domain.repositories.AccountRepository;
import com.picpay.transaction_processing.domain.repositories.TransactionRepository;
import com.picpay.shared.domain.value_objects.Money;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class CreateTransferUseCase {
    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;
    private final TransactionAuthorizerPort transactionAuthorizer;

    public CreateTransferUseCase(
        AccountRepository accountRepository,
        TransactionRepository transactionRepository,
        TransactionAuthorizerPort transactionAuthorizer
    ) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
        this.transactionAuthorizer = transactionAuthorizer;
    }

    @Transactional
    public void execute(CreateTransferDTO dto) {
        var payerId = new AccountId(dto.payer());
        var payeeId = new AccountId(dto.payee());

        if (payerId.equals(payeeId)) {
            throw new RecursiveTransferException();
        }

        var payerAccount = accountRepository
            .findById(payerId)
            .orElseThrow(() -> new PayerNotFoundException(payerId));
        var payeeAccount = accountRepository
            .findById(payeeId)
            .orElseThrow(() -> new PayeeNotFoundException(payeeId));
        var value = new Money(dto.value(), payerAccount.currency());

        payerAccount.debit(transactionAuthorizer, value);
        payeeAccount.credit(transactionAuthorizer, value);

        var transaction = new Transaction(
            transactionRepository,
            value,
            payerAccount.id(),
            payeeAccount.id()
        );

        accountRepository.save(payerAccount);
        accountRepository.save(payeeAccount);
        transactionRepository.save(transaction);
    }
}

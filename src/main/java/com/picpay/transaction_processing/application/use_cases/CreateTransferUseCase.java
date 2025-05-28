package com.picpay.transaction_processing.application.use_cases;

import com.picpay.shared.domain.entities.AccountId;
import com.picpay.transaction_processing.domain.dtos.CreateTransferDTO;
import com.picpay.transaction_processing.domain.entities.Transaction;
import com.picpay.transaction_processing.domain.exceptions.PayeeNotFoundException;
import com.picpay.transaction_processing.domain.exceptions.PayerNotFoundException;
import com.picpay.transaction_processing.domain.exceptions.RecursiveTransferException;
import com.picpay.transaction_processing.domain.ports.CreditTransactionAuthorizerPort;
import com.picpay.transaction_processing.domain.ports.DebitTransactionAuthorizerPort;
import com.picpay.transaction_processing.domain.repositories.AccountRepository;
import com.picpay.transaction_processing.domain.repositories.TransactionRepository;
import com.picpay.shared.domain.value_objects.Money;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

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
                .findById(new AccountId(dto.payee()))
                .orElseThrow(() -> new PayeeNotFoundException(payeeId));
        var amount = new Money(dto.value(), payerAccount.currency());

        var transaction = new Transaction(
            transactionRepository,
            amount,
            payerAccount.id(),
            payeeAccount.id()
        );

        payerAccount.debit(debitTransactionAuthorizer, amount);
        payeeAccount.credit(creditTransactionAuthorizer, amount);

        accountRepository.save(payerAccount);
        accountRepository.save(payeeAccount);
        transactionRepository.save(transaction);
    }
}

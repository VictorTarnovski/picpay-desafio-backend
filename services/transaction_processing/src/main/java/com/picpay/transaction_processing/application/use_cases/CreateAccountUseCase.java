package com.picpay.transaction_processing.application.use_cases;

import com.picpay.shared.domain.events.AccountOpened;
import com.picpay.shared.domain.value_objects.Money;
import com.picpay.transaction_processing.domain.entities.Account;
import com.picpay.transaction_processing.domain.repositories.AccountRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class CreateAccountUseCase {
    private final AccountRepository repository;

    public CreateAccountUseCase(AccountRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public void execute(AccountOpened accountOpened) {
        var account = new Account(accountOpened.id(), accountOpened.type(), new Money(accountOpened.currency()));
        repository.save(account);
    }
}

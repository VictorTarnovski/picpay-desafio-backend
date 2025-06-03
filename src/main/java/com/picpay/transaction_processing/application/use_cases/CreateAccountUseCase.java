package com.picpay.transaction_processing.application.use_cases;

import com.picpay.shared.domain.events.AccountCreated;
import com.picpay.shared.domain.value_objects.Money;
import com.picpay.transaction_processing.domain.entities.Account;
import com.picpay.transaction_processing.domain.repositories.AccountRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

@Component
public class CreateAccountUseCase {
    private final AccountRepository repository;

    public CreateAccountUseCase(AccountRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public void execute(AccountCreated accountCreated) {
        var account = new Account(accountCreated.id(), new Money(accountCreated.currency()));
        repository.save(account);
    }
}

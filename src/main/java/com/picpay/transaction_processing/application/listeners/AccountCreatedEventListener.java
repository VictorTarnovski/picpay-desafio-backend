package com.picpay.transaction_processing.application.listeners;

import com.picpay.account_management.domain.events.AccountCreated;
import com.picpay.shared.domain.value_objects.Money;
import com.picpay.transaction_processing.domain.entities.Account;
import com.picpay.transaction_processing.domain.repositories.AccountRepository;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class AccountCreatedEventListener {
    private final AccountRepository repository;

    public AccountCreatedEventListener(AccountRepository repository) {
        this.repository = repository;
    }

    @EventListener
    public void onAccountCreated(AccountCreated accountCreated) {
        // TODO: Implement OutBoxMesage Pattern
        var account = new Account(accountCreated.id(), new Money(accountCreated.currency()));
        repository.save(account);
    }
}

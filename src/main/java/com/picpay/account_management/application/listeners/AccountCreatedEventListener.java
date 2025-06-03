package com.picpay.account_management.application.listeners;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.picpay.account_management.domain.entities.OutBoxMessage;
import com.picpay.shared.domain.entities.BoxMessage;
import com.picpay.shared.domain.events.AccountCreated;
import com.picpay.account_management.domain.repositories.OutBoxMessageRepository;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
class AccountCreatedEventListener {
    OutBoxMessageRepository repository;

    AccountCreatedEventListener(OutBoxMessageRepository repository) {
        this.repository = repository;
    }

    @EventListener
    void onAccountCreated(AccountCreated accountCreated) throws JsonProcessingException {
        var outbox = BoxMessage
            .of(accountCreated)
            .map(OutBoxMessage::new);
        repository.save(outbox);
    }
}

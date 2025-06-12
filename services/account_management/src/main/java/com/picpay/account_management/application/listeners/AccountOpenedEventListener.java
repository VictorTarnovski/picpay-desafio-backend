package com.picpay.account_management.application.listeners;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.picpay.shared.domain.entities.OutBoxMessage;
import com.picpay.shared.domain.entities.BoxMessage;
import com.picpay.shared.domain.events.AccountOpened;
import com.picpay.shared.domain.repositories.OutBoxMessageRepository;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
class AccountOpenedEventListener {
    private final OutBoxMessageRepository repository;

    AccountOpenedEventListener(OutBoxMessageRepository repository) {
        this.repository = repository;
    }

    @EventListener
    void onAccountOpened(AccountOpened accountOpened) throws JsonProcessingException {
        var outbox = BoxMessage
            .of(accountOpened)
            .map(OutBoxMessage::new);
        repository.save(outbox);
    }
}

package com.picpay.auth.application.listeners;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.picpay.shared.domain.entities.OutBoxMessage;
import com.picpay.shared.domain.entities.BoxMessage;
import com.picpay.shared.domain.repositories.OutBoxMessageRepository;
import com.picpay.shared.domain.events.UserRegistered;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
class UserRegisteredEventListener {
    private final OutBoxMessageRepository repository;

    UserRegisteredEventListener(OutBoxMessageRepository repository) {
        this.repository = repository;
    }

    @EventListener
    void onUserRegistered(UserRegistered userRegistered) throws JsonProcessingException {
        var outbox = BoxMessage
            .of(userRegistered)
            .map(OutBoxMessage::new);
        repository.save(outbox);
    }
}

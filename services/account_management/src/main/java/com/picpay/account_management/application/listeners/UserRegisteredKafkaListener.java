package com.picpay.account_management.application.listeners;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.picpay.shared.domain.entities.BoxMessage;
import com.picpay.shared.domain.events.UserRegistered;
import com.picpay.shared.domain.entities.InBoxMessage;
import com.picpay.shared.domain.repositories.InBoxMessageRepository;
import jakarta.transaction.Transactional;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class UserRegisteredKafkaListener {
    private final InBoxMessageRepository repository;

    public UserRegisteredKafkaListener(InBoxMessageRepository repository) {
        this.repository = repository;
    }

    @Transactional
    @KafkaListener(
        topics = "userRegistered",
        groupId = "auth",
        containerFactory = "userRegisteredKafkaListenerContainerFactory")
    public void onUserRegistered(@Payload UserRegistered userRegistered) throws JsonProcessingException {
        var inbox = BoxMessage
            .of(userRegistered)
            .map(InBoxMessage::new);
        repository.save(inbox);
    }
}

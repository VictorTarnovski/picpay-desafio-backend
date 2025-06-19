package com.picpay.transaction_processing.application.listeners;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.picpay.shared.domain.entities.BoxMessage;
import com.picpay.shared.domain.events.AccountOpened;
import com.picpay.shared.domain.entities.InBoxMessage;
import com.picpay.shared.domain.repositories.InBoxMessageRepository;
import jakarta.transaction.Transactional;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class AccountOpenedKafkaListener {
    private final InBoxMessageRepository repository;

    public AccountOpenedKafkaListener(InBoxMessageRepository repository) {
        this.repository = repository;
    }

    @Transactional
    @KafkaListener(
        topics = "accountOpened",
        groupId = "account_management",
        containerFactory = "accountOpenedKafkaListenerContainerFactory")
    public void onAccountOpened(@Payload AccountOpened accountOpened) throws JsonProcessingException {
        var inbox = BoxMessage
            .of(accountOpened)
            .map(InBoxMessage::new);
        repository.save(inbox);
    }
}

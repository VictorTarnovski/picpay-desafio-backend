package com.picpay.account_management.application.use_cases;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.picpay.shared.domain.entities.OutBoxMessage;
import com.picpay.shared.domain.repositories.OutBoxMessageRepository;
import com.picpay.shared.domain.events.AccountOpened;
import jakarta.transaction.Transactional;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

// Created to ensure @Transactional works
@Component
public class SendAccountOpenedMessageUseCase {
    private final KafkaTemplate<String, AccountOpened> kafka;
    private final OutBoxMessageRepository repository;

    public SendAccountOpenedMessageUseCase(
        KafkaTemplate<String, AccountOpened> kafka,
        OutBoxMessageRepository repository) {
        this.kafka = kafka;
        this.repository = repository;
    }

    @Transactional
    public void execute(OutBoxMessage message) throws JsonProcessingException {
        var accountOpened = new ObjectMapper().readValue(message.payload, AccountOpened.class);
        kafka.send("accountOpened", accountOpened);
        message.processed = true;
        repository.save(message);
    }
}

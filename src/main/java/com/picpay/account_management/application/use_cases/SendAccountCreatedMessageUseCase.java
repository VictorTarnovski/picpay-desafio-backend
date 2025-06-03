package com.picpay.account_management.application.use_cases;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.picpay.account_management.domain.entities.OutBoxMessage;
import com.picpay.account_management.domain.repositories.OutBoxMessageRepository;
import com.picpay.shared.domain.events.AccountCreated;
import jakarta.transaction.Transactional;
import org.springframework.kafka.core.KafkaTemplate;

// Created to ensure @Transactional works
public class SendAccountCreatedMessageUseCase {
    private final KafkaTemplate<String, AccountCreated> kafka;
    private final OutBoxMessageRepository repository;

    public SendAccountCreatedMessageUseCase(
        KafkaTemplate<String, AccountCreated> kafka,
        OutBoxMessageRepository repository) {
        this.kafka = kafka;
        this.repository = repository;
    }

    @Transactional
    public void execute(OutBoxMessage message) throws JsonProcessingException {
        var accountCreated = new ObjectMapper().readValue(message.payload, AccountCreated.class);
        kafka.send("accountCreated", accountCreated);
        message.processed = true;
        repository.save(message);
    }
}

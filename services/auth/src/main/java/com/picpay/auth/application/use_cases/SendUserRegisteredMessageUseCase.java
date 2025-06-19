package com.picpay.auth.application.use_cases;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.picpay.shared.domain.entities.OutBoxMessage;
import com.picpay.shared.domain.repositories.OutBoxMessageRepository;
import com.picpay.shared.domain.events.UserRegistered;
import jakarta.transaction.Transactional;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

// Created to ensure @Transactional works
@Component
public class SendUserRegisteredMessageUseCase {
    private final KafkaTemplate<String, UserRegistered> kafka;
    private final OutBoxMessageRepository repository;

    public SendUserRegisteredMessageUseCase(
        KafkaTemplate<String, UserRegistered> kafka,
        OutBoxMessageRepository repository
    ) {
        this.kafka = kafka;
        this.repository = repository;
    }

    @Transactional
    public void execute(OutBoxMessage message) throws JsonProcessingException {
        var userRegistered = new ObjectMapper().readValue(message.payload, UserRegistered.class);
        kafka
        .send("userRegistered", userRegistered)
        .whenComplete((result, ex) -> {
            if(ex != null) return;
            message.processed = true;
            repository.save(message);
        });
    }
}

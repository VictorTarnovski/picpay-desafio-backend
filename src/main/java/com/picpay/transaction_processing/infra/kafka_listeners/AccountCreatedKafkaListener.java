package com.picpay.transaction_processing.infra.kafka_listeners;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.picpay.shared.domain.entities.BoxMessage;
import com.picpay.shared.domain.events.AccountCreated;
import com.picpay.transaction_processing.domain.entities.InBoxMessage;
import com.picpay.transaction_processing.domain.repositories.InBoxMessageRepository;
import jakarta.transaction.Transactional;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
class AccountCreatedKafkaListener {
    private final InBoxMessageRepository repository;

    AccountCreatedKafkaListener(InBoxMessageRepository repository) {
        this.repository = repository;
    }

    @Transactional
    @KafkaListener(topics = "accountCreated")
    void execute(AccountCreated accountCreated) throws JsonProcessingException {
        var inbox = BoxMessage
            .of(accountCreated)
            .map(InBoxMessage::new);
        repository.save(inbox);
    }
}

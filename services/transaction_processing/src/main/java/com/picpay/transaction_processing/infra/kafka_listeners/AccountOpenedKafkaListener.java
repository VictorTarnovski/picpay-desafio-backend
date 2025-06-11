package com.picpay.transaction_processing.infra.kafka_listeners;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.picpay.shared.domain.entities.BoxMessage;
import com.picpay.shared.domain.events.AccountOpened;
import com.picpay.shared.domain.entities.InBoxMessage;
import com.picpay.shared.domain.repositories.InBoxMessageRepository;
import jakarta.transaction.Transactional;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
class AccountOpenedKafkaListener {
    private final InBoxMessageRepository repository;

    AccountOpenedKafkaListener(InBoxMessageRepository repository) {
        this.repository = repository;
    }

    @Transactional
    @KafkaListener(topics = "accountOpened", groupId = "transaction_processing")
    void execute(AccountOpened accountOpened) throws JsonProcessingException {
        var inbox = BoxMessage
            .of(accountOpened)
            .map(InBoxMessage::new);
        repository.save(inbox);
    }
}

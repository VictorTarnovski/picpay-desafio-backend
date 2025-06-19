package com.picpay.auth.application.scheduled_jobs;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.picpay.auth.application.use_cases.SendUserRegisteredMessageUseCase;
import com.picpay.shared.domain.repositories.OutBoxMessageRepository;
import com.picpay.shared.domain.entities.OutBoxMessage;
import com.picpay.shared.domain.events.UserRegistered;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
class UserRegisteredOutBoxMessageProducerJob {
    private final OutBoxMessageRepository repository;
    private final SendUserRegisteredMessageUseCase sender;

    UserRegisteredOutBoxMessageProducerJob(OutBoxMessageRepository repository, SendUserRegisteredMessageUseCase sender) {
        this.repository = repository;
        this.sender = sender;
    }

    @Scheduled(fixedRate = 15000)
    void execute() throws JsonProcessingException {
        var messages = repository.findUnprocessedByType(UserRegistered.class.getTypeName());

        for (OutBoxMessage message : messages) {
            sender.execute(message);
        }
    }
}

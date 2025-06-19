package com.picpay.account_management.application.scheduled_jobs;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.picpay.shared.domain.repositories.InBoxMessageRepository;
import com.picpay.account_management.application.use_cases.ConsumeUserRegisteredMessageUseCase;
import com.picpay.shared.domain.events.UserRegistered;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
class UserRegisteredInBoxMessageConsumerJob {
    private final InBoxMessageRepository repository;
    private final ConsumeUserRegisteredMessageUseCase useCase;

    UserRegisteredInBoxMessageConsumerJob(
        InBoxMessageRepository repository,
        ConsumeUserRegisteredMessageUseCase useCase
    ) {
        this.repository = repository;
        this.useCase = useCase;
    }

    @Scheduled(fixedRate = 15000)
    void execute() throws JsonProcessingException {
        var messages = repository.findUnprocessedByType(UserRegistered.class.getTypeName());

        for (var message : messages) {
            useCase.execute(message);
        }
    }
}

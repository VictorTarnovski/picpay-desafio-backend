package com.picpay.account_management.application.scheduled_jobs;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.picpay.account_management.application.use_cases.SendAccountOpenedMessageUseCase;
import com.picpay.shared.domain.repositories.OutBoxMessageRepository;
import com.picpay.shared.domain.events.AccountOpened;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
class AccountOpenedOutBoxMessageProducerJob {
    private final OutBoxMessageRepository repository;
    private final SendAccountOpenedMessageUseCase sender;

    AccountOpenedOutBoxMessageProducerJob(
        OutBoxMessageRepository repository,
        SendAccountOpenedMessageUseCase sender
    ) {
        this.repository = repository;
        this.sender = sender;
    }

    @Scheduled(fixedRate = 15000)
    void execute() throws JsonProcessingException {
        var messages = repository.findUnprocessedByType(AccountOpened.class.getTypeName());

        for (var message : messages) {
            sender.execute(message);
        }
    }
}

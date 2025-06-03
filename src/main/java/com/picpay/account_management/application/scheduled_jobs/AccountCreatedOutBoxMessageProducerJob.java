package com.picpay.account_management.application.scheduled_jobs;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.picpay.account_management.application.use_cases.SendAccountCreatedMessageUseCase;
import com.picpay.account_management.domain.entities.OutBoxMessage;
import com.picpay.account_management.domain.repositories.OutBoxMessageRepository;
import com.picpay.shared.domain.events.AccountCreated;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
class AccountCreatedOutBoxMessageProducerJob {
    private final OutBoxMessageRepository repository;
    private final SendAccountCreatedMessageUseCase sender;

    AccountCreatedOutBoxMessageProducerJob(OutBoxMessageRepository repository, SendAccountCreatedMessageUseCase sender) {
        this.repository = repository;
        this.sender = sender;
    }

    @Scheduled(fixedRate = 60000)
    void execute() throws JsonProcessingException {
        var messages = repository.findUnprocessedByType(AccountCreated.class.getTypeName(), PageRequest.ofSize(30)).getContent();

        for (OutBoxMessage message : messages) {
            sender.execute(message);
        }
    }
}

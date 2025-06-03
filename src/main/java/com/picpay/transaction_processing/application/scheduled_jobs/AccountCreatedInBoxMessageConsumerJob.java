package com.picpay.transaction_processing.application.scheduled_jobs;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.picpay.shared.domain.events.AccountCreated;
import com.picpay.transaction_processing.application.use_cases.CreateAccountUseCase;
import com.picpay.transaction_processing.domain.entities.InBoxMessage;
import com.picpay.transaction_processing.domain.repositories.InBoxMessageRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
class AccountCreatedInBoxMessageConsumerJob {
    private final InBoxMessageRepository repository;
    private final CreateAccountUseCase createAccountUseCase;

    AccountCreatedInBoxMessageConsumerJob(
        InBoxMessageRepository repository,
        CreateAccountUseCase createAccountUseCase) {
        this.repository = repository;
        this.createAccountUseCase = createAccountUseCase;
    }

    @Scheduled(fixedRate = 60000)
    void execute() throws JsonProcessingException {
        var messages = repository
            .findUnprocessedByType(
                AccountCreated.class.getTypeName(),
                PageRequest.ofSize(30))
            .getContent();

        var objectMapper = new ObjectMapper();
        for (InBoxMessage message : messages) {
            var accountCreated = objectMapper.readValue(message.payload, AccountCreated.class);
            createAccountUseCase.execute(accountCreated);
        }
    }
}

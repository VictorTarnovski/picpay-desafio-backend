package com.picpay.transaction_processing.application.scheduled_jobs;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.picpay.shared.domain.events.AccountOpened;
import com.picpay.transaction_processing.application.use_cases.CreateAccountUseCase;
import com.picpay.shared.domain.entities.InBoxMessage;
import com.picpay.shared.domain.repositories.InBoxMessageRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
class AccountOpenedInBoxMessageConsumerJob {
    private final InBoxMessageRepository repository;
    private final CreateAccountUseCase createAccountUseCase;

    AccountOpenedInBoxMessageConsumerJob(
        InBoxMessageRepository repository,
        CreateAccountUseCase createAccountUseCase) {
        this.repository = repository;
        this.createAccountUseCase = createAccountUseCase;
    }

    @Scheduled(fixedRate = 60000)
    void execute() throws JsonProcessingException {
        var messages = repository.findUnprocessedByType(AccountOpened.class.getTypeName());

        var objectMapper = new ObjectMapper();

        for (InBoxMessage message : messages) {
            var accountOpened = objectMapper.readValue(message.payload, AccountOpened.class);
            createAccountUseCase.execute(accountOpened);
        }
    }
}

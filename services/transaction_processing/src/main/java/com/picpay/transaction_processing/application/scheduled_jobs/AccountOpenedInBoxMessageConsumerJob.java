package com.picpay.transaction_processing.application.scheduled_jobs;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.picpay.shared.domain.repositories.InBoxMessageRepository;
import com.picpay.transaction_processing.application.use_cases.ConsumeAccountOpenedMessageUseCase;
import com.picpay.shared.domain.events.AccountOpened;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
class AccountOpenedInBoxMessageConsumerJob {
    private final InBoxMessageRepository repository;
    private final ConsumeAccountOpenedMessageUseCase useCase;

    AccountOpenedInBoxMessageConsumerJob(
        InBoxMessageRepository repository,
        ConsumeAccountOpenedMessageUseCase useCase
    ) {
        this.repository = repository;
        this.useCase = useCase;
    }

    @Scheduled(fixedRate = 15000)
    void execute() throws JsonProcessingException {
        var messages = repository.findUnprocessedByType(AccountOpened.class.getTypeName());

        for (var message : messages) {
            useCase.execute(message);
        }
    }
}

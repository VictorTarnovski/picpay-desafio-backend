package com.picpay.transaction_processing.application.scheduled_jobs;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.picpay.shared.domain.repositories.InBoxMessageRepository;
import com.picpay.transaction_processing.application.use_cases.ConsumeFundReceivedMessageUseCase;
import com.picpay.transaction_processing.domain.events.FundReceived;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
class FundReceivedInBoxMessageConsumerJob {
    private final InBoxMessageRepository repository;
    private final ConsumeFundReceivedMessageUseCase useCase;

    FundReceivedInBoxMessageConsumerJob(
        InBoxMessageRepository repository,
        ConsumeFundReceivedMessageUseCase useCase
    ) {
        this.repository = repository;
        this.useCase = useCase;
    }

    @Scheduled(fixedRate = 15000)
    void execute() throws JsonProcessingException {
        var messages = repository.findUnprocessedByType(FundReceived.class.getTypeName());

        for (var message : messages) {
            useCase.execute(message);
        }
    }
}

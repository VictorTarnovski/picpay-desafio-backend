package com.picpay.transaction_processing.application.use_cases;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.picpay.shared.domain.entities.InBoxMessage;
import com.picpay.transaction_processing.domain.events.FundReceived;
import com.picpay.shared.domain.repositories.InBoxMessageRepository;

import jakarta.transaction.Transactional;

@Transactional
@Service
public class ConsumeFundReceivedMessageUseCase {
    private final InBoxMessageRepository repository;
    private final NotifyReceivedFundUseCase useCase;

    public ConsumeFundReceivedMessageUseCase(
        InBoxMessageRepository repository,
        NotifyReceivedFundUseCase useCase
    ) {
        this.repository = repository;
        this.useCase = useCase;
    }

    public void execute(InBoxMessage message) throws JsonMappingException, JsonProcessingException {
        var fundReceived = new ObjectMapper().readValue(message.payload, FundReceived.class);
        useCase.execute(fundReceived.accountId(),  fundReceived.value());
        message.processed = true;
        repository.save(message);
    }
}

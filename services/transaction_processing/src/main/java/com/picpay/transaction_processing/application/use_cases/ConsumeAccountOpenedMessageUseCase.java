package com.picpay.transaction_processing.application.use_cases;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.picpay.transaction_processing.domain.dtos.OpenAccountDTO;
import com.picpay.shared.domain.entities.InBoxMessage;
import com.picpay.shared.domain.events.AccountOpened;
import com.picpay.shared.domain.repositories.InBoxMessageRepository;

import jakarta.transaction.Transactional;

@Transactional
@Service
public class ConsumeAccountOpenedMessageUseCase {
    private final InBoxMessageRepository repository;
    private final OpenAccountUseCase useCase;

    public ConsumeAccountOpenedMessageUseCase(
        InBoxMessageRepository repository,
        OpenAccountUseCase useCase
    ) {
        this.repository = repository;
        this.useCase = useCase;
    }

    public void execute(InBoxMessage message) throws JsonMappingException, JsonProcessingException {
        var accountOpened = new ObjectMapper().readValue(message.payload, AccountOpened.class);
        var dto = new OpenAccountDTO(
            accountOpened.id(),
            accountOpened.type(),
            accountOpened.currency()
        );
        useCase.execute(dto);
        message.processed = true;
        repository.save(message);
    }
}

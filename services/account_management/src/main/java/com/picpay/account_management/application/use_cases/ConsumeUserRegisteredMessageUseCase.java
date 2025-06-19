package com.picpay.account_management.application.use_cases;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.picpay.account_management.domain.dtos.OpenAccountDTO;
import com.picpay.shared.domain.entities.InBoxMessage;
import com.picpay.shared.domain.events.UserRegistered;
import com.picpay.shared.domain.repositories.InBoxMessageRepository;

import jakarta.transaction.Transactional;

@Transactional
@Service
public class ConsumeUserRegisteredMessageUseCase {
    private final InBoxMessageRepository repository;
    private final OpenAccountUseCase useCase;

    public ConsumeUserRegisteredMessageUseCase(
        InBoxMessageRepository repository,
        OpenAccountUseCase useCase
    ) {
        this.repository = repository;
        this.useCase = useCase;
    }

    public void execute(InBoxMessage message) throws JsonMappingException, JsonProcessingException {
        var userRegistered = new ObjectMapper().readValue(message.payload, UserRegistered.class);
        var dto = new OpenAccountDTO(
            userRegistered.accountType(),
            userRegistered.id()
        );
        useCase.execute(dto);
        message.processed = true;
        repository.save(message);
    }
}

package com.picpay.transaction_processing.application.use_cases;

import com.picpay.shared.domain.value_objects.Money;
import com.picpay.transaction_processing.domain.dtos.OpenAccountDTO;
import com.picpay.transaction_processing.domain.entities.Account;
import com.picpay.transaction_processing.domain.repositories.AccountRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class OpenAccountUseCase {
    private final AccountRepository repository;

    public OpenAccountUseCase(AccountRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public void execute(OpenAccountDTO dto) {
        var account = new Account(
            dto.id(),
            dto.type(),
            new Money(dto.currency())
        );
        repository.save(account);
    }
}

package com.picpay.account_management.application.use_cases;

import java.util.Currency;

import org.springframework.stereotype.Service;

import com.picpay.account_management.domain.dtos.OpenAccountDTO;
import com.picpay.account_management.domain.entities.Account;
import com.picpay.account_management.domain.repositories.AccountRepository;

import jakarta.transaction.Transactional;

@Service
public class OpenAccountUseCase {
    private final AccountRepository repository;

    public OpenAccountUseCase(AccountRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public void execute(OpenAccountDTO dto) {
        var existingAccount = repository.findByUserId(dto.userId());
        if (existingAccount.isPresent()) return;

        var account = new Account(
            repository,
            dto.type(),
            Currency.getInstance("BRL"),
            dto.userId());

        repository.save(account);
    }
}

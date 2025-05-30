package com.picpay.account_management.application.adapters;

import com.picpay.account_management.domain.entities.Account;
import com.picpay.account_management.domain.ports.AccountManagementModulePort;
import com.picpay.account_management.domain.repositories.AccountRepository;
import com.picpay.shared.domain.entities.AccountId;
import com.picpay.shared.domain.enums.AccountType;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class AccountManagementModuleAdapter implements AccountManagementModulePort {
    private final AccountRepository repository;

    public AccountManagementModuleAdapter(AccountRepository repository) {
        this.repository = repository;
    }

    @Override
    public AccountType type(AccountId id) {
        Objects.requireNonNull(id, "id must not be null");
        return repository.findById(id).map(Account::type).get();
    }
}

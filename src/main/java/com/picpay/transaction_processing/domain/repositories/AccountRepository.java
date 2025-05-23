package com.picpay.transaction_processing.domain.repositories;

import com.picpay.shared.domain.entities.AccountId;
import com.picpay.transaction_processing.domain.entities.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends CrudRepository<Account, AccountId> {
}

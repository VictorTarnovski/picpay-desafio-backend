package com.picpay.transaction_processing.domain.repositories;

import com.picpay.shared.domain.ids.AccountId;
import com.picpay.transaction_processing.domain.entities.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("TransactionProcessingAccountRepository")
public interface AccountRepository extends CrudRepository<Account, AccountId> {
}

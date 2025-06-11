package com.picpay.account_management.domain.repositories;

import com.picpay.account_management.domain.entities.Account;
import com.picpay.shared.domain.ids.AccountId;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("AccountManagementAccountRepository")
public interface AccountRepository extends CrudRepository<Account, AccountId> {
    @Query(value = "SELECT nextval('account_management.accounts_id_seq')", nativeQuery = true)
    AccountId nextId();
}

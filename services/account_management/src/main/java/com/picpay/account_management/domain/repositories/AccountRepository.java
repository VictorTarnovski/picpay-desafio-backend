package com.picpay.account_management.domain.repositories;

import com.picpay.account_management.domain.entities.Account;
import com.picpay.shared.domain.ids.AccountId;
import com.picpay.shared.domain.ids.UserId;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends CrudRepository<Account, AccountId> {
    @Query(value = "SELECT nextval('account_management.accounts_id_seq')", nativeQuery = true)
    AccountId nextId();

    @Query("SELECT a FROM Account a WHERE a.userId = :userId")
    Optional<Account> findByUserId(UserId userId);
}

package com.picpay.transaction_processing.domain.repositories;

import com.picpay.transaction_processing.domain.entities.Transaction;
import com.picpay.transaction_processing.domain.entities.TransactionId;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends CrudRepository<Transaction, TransactionId> {
    @Query(value = "SELECT nextval('transaction_processing.transactions_id_seq')", nativeQuery = true)
    TransactionId nextId();
}

package com.picpay.transaction_processing.domain.repositories;

import com.picpay.transaction_processing.domain.entities.InBoxMessage;
import org.springframework.stereotype.Repository;

@Repository("transaction_processing.InBoxMessageRepository")
public interface InBoxMessageRepository extends com.picpay.shared.domain.repositories.InBoxMessageRepository<InBoxMessage> {
}

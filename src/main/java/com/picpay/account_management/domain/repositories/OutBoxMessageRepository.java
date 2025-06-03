package com.picpay.account_management.domain.repositories;

import com.picpay.account_management.domain.entities.OutBoxMessage;
import org.springframework.stereotype.Repository;

@Repository("account_management.OutBoxMessageRepository")
public interface OutBoxMessageRepository extends com.picpay.shared.domain.repositories.OutBoxMessageRepository<OutBoxMessage> {
}

package com.picpay.shared.domain.repositories;

import com.picpay.shared.domain.entities.OutBoxMessage;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.UUID;
import java.util.List;

public interface OutBoxMessageRepository extends CrudRepository<OutBoxMessage, UUID> {
    @Query(
        value = "SELECT * FROM outbox_messages WHERE processed = false and type = :type ORDER BY created_at ASC",
        nativeQuery = true
    )
    List<OutBoxMessage> findUnprocessedByType(@Param("type") String type);
}

package com.picpay.shared.domain.repositories;

import com.picpay.shared.domain.entities.InBoxMessage;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.UUID;
import java.util.List;

public interface InBoxMessageRepository extends CrudRepository<InBoxMessage, UUID> {
    @Query(
        value = "SELECT * FROM inbox_messages WHERE processed = false and type = :type ORDER BY created_at ASC",
        nativeQuery = true
    )
    List<InBoxMessage> findUnprocessedByType(@Param("type") String type);
}

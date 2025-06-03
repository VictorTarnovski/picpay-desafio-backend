package com.picpay.shared.domain.repositories;

import com.picpay.shared.domain.entities.BoxMessage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface InBoxMessageRepository<T extends BoxMessage> extends CrudRepository<T, UUID> {
    @Query("SELECT m FROM inbox_messages AS m WHERE m.processed = false and m.type = :type ORDER BY m.created_at ASC")
    Page<T> findUnprocessedByType(@Param("type") Class<?> type, Pageable pageable);
}

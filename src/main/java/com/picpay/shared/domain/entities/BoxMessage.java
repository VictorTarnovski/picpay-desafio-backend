package com.picpay.shared.domain.entities;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.picpay.shared.domain.builders.BoxMessageBuilder;
import jakarta.persistence.*;

import java.time.OffsetDateTime;
import java.util.Objects;
import java.util.UUID;
import java.util.function.Function;

@MappedSuperclass
public class BoxMessage {
    @Id
    public UUID id;
    public String type;
    public String payload;
    public boolean processed;
    public OffsetDateTime createdAt;

    private static BoxMessageBuilder builder() {
        return new BoxMessageBuilder();
    }

    public static BoxMessage of(Object o) throws JsonProcessingException {
        return builder()
            .type(o.getClass().getTypeName())
            .payload(new ObjectMapper().writeValueAsString(o))
            .build();
    }

    public <U> U map(Function<? super BoxMessage, ? extends U> mapper) {
        Objects.requireNonNull(mapper);
        return mapper.apply(this);
    }
}

package com.picpay.shared.domain.builders;

import com.picpay.shared.domain.entities.BoxMessage;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.UUID;

public class BoxMessageBuilder {
    private final UUID id;
    private String type;
    private String payload;
    private final boolean processed;
    private final OffsetDateTime createdAt;

    public BoxMessageBuilder() {
        id = UUID.randomUUID();
        processed = false;
        createdAt = Instant.now().atOffset(ZoneOffset.UTC);
    }

    public BoxMessageBuilder type(String type) {
        this.type = type;
        return this;
    }

    public BoxMessageBuilder payload(String payload) {
        this.payload = payload;
        return this;
    }

    public BoxMessage build() {
        var message = new BoxMessage();
        message.id = id;
        message.type = type;
        message.payload = payload;
        message.processed = processed;
        message.createdAt = createdAt;
        return message;
    }

}

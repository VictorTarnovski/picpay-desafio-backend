package com.picpay.shared.domain.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "outbox_messages")
public class OutBoxMessage extends BoxMessage {
    public OutBoxMessage(BoxMessage message) {
        id = message.id;
        type = message.type;
        payload = message.payload;
        processed = message.processed;
        createdAt = message.createdAt;
    }
}

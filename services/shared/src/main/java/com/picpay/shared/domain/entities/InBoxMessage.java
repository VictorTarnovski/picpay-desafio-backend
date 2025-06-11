package com.picpay.shared.domain.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "inbox_messages")
public class InBoxMessage extends BoxMessage {
    public InBoxMessage(BoxMessage message) {
        id = message.id;
        type = message.type;
        payload = message.payload;
        processed = message.processed;
        createdAt = message.createdAt;
    }
}

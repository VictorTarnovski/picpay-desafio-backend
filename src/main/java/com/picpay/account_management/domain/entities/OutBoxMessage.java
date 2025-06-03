package com.picpay.account_management.domain.entities;

import com.picpay.shared.domain.entities.BoxMessage;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "outbox_messages", schema = "account_management")
public class OutBoxMessage extends BoxMessage {
    public OutBoxMessage(BoxMessage message) {
        id = message.id;
        type = message.type;
        payload = message.payload;
        processed = message.processed;
        createdAt = message.createdAt;
    }
}

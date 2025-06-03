package com.picpay.transaction_processing.domain.entities;

import com.picpay.shared.domain.entities.BoxMessage;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "inbox_messages", schema = "transaction_processing")
public class InBoxMessage extends BoxMessage {
    public InBoxMessage(BoxMessage message) {
        id = message.id;
        type = message.type;
        payload = message.payload;
        processed = message.processed;
        createdAt = message.createdAt;
    }
}

package com.picpay.transaction_processing.application.listeners;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.picpay.shared.domain.entities.InBoxMessage;
import com.picpay.shared.domain.entities.BoxMessage;
import com.picpay.shared.domain.repositories.InBoxMessageRepository;
import com.picpay.transaction_processing.domain.events.FundReceived;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
class FundReceivedEventListener {
    private final InBoxMessageRepository repository;

    FundReceivedEventListener(InBoxMessageRepository repository) {
        this.repository = repository;
    }

    @EventListener
    void onFundReceived(FundReceived fundReceived) throws JsonProcessingException {
        var inbox = BoxMessage
            .of(fundReceived)
            .map(InBoxMessage::new);
        repository.save(inbox);
    }
}

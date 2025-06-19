package com.picpay.transaction_processing.application.use_cases;

import com.picpay.shared.domain.ids.AccountId;
import com.picpay.shared.domain.value_objects.Money;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class NotifyReceivedFundUseCase {
    public void execute(AccountId accountId, Money value) {
        RestClient
            .create("https://util.devi.tools/api/v1/notify")
            .post()
            .retrieve()
            .toBodilessEntity();
    }
}

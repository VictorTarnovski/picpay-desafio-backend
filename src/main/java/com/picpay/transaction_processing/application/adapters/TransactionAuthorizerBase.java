package com.picpay.transaction_processing.application.adapters;

import com.picpay.transaction_processing.domain.exceptions.UnauthorizedTransactionException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class TransactionAuthorizerBase {
    public void authorize() {
        RestClient
            .create("https://util.devi.tools/api/v2/authorize")
            .get()
            .retrieve()
            .onStatus(status -> status == HttpStatus.FORBIDDEN, (request, response) -> {
                throw new UnauthorizedTransactionException();
            })
            .toBodilessEntity();
    }
}

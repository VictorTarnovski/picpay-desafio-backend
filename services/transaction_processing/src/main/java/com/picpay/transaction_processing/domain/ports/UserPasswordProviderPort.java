package com.picpay.transaction_processing.domain.ports;

@FunctionalInterface
public interface UserPasswordProviderPort {
    String provide();
}

package com.picpay.transaction_processing.domain.dtos;

import jakarta.validation.constraints.Min;

public record CreateTransferDto(
    @Min(1)
    long value,
    @Min(1)
    long payer,
    @Min(1)
    long payee) { }

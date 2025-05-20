package com.picpay.transfers.domain.dtos;

import jakarta.validation.constraints.Min;

public record CreateTransferDto(
    @Min(1)
    int value,
    @Min(1)
    int payer,
    @Min(1)
    int payee) { }

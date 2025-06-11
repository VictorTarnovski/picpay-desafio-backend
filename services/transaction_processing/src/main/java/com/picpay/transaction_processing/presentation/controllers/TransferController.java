package com.picpay.transaction_processing.presentation.controllers;

import com.picpay.transaction_processing.application.use_cases.CreateTransferUseCase;
import com.picpay.transaction_processing.domain.dtos.CreateTransferDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transfer")
public class TransferController {
    private final CreateTransferUseCase createTransferUseCase;

    public TransferController(CreateTransferUseCase createTransferUseCase) {
        this.createTransferUseCase = createTransferUseCase;
    }

    @PostMapping
    public ResponseEntity<?> Create(
        @Valid
        @RequestBody
        CreateTransferDTO dto
    ) {
        createTransferUseCase.execute(dto);
        return ResponseEntity.noContent().build();
    }
}

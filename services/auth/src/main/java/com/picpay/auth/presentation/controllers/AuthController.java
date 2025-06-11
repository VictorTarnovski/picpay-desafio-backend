package com.picpay.auth.presentation.controllers;

import com.picpay.auth.application.use_cases.RegisterUserUseCase;
import com.picpay.auth.domain.dtos.RegisterUserDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final RegisterUserUseCase registerUserUseCase;

    public AuthController(RegisterUserUseCase registerUserUseCase) {
        this.registerUserUseCase = registerUserUseCase;
    }

    @PostMapping("/users/register")
    public ResponseEntity<?> Register(
        @RequestBody
        @Valid
        RegisterUserDTO dto
    ) {
        registerUserUseCase.execute(dto);
        return ResponseEntity.noContent().build();
    }
}

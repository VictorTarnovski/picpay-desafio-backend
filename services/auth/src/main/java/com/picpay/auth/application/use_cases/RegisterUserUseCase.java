package com.picpay.auth.application.use_cases;

import com.picpay.auth.domain.dtos.RegisterUserDTO;
import com.picpay.auth.domain.entities.User;
import com.picpay.auth.domain.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class RegisterUserUseCase {
    private final UserRepository userRepository;

    public RegisterUserUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void execute(RegisterUserDTO dto) {
        var otp = "";
        var user = new User(
            userRepository,
            dto.fullName(),
            dto.document(),
            dto.email(),
            otp,
            dto.accountType()
        );

        userRepository.save(user);
    }
}

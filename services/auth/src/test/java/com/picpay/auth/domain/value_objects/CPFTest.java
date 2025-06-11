package com.picpay.auth.domain.value_objects;

import com.picpay.auth.domain.exceptions.InvalidCPFException;
import com.picpay.auth.domain.exceptions.InvalidCPFLenghtException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CPFTest {
    @Test
    void given_a_valid_input_when_creating_a_cpf_then_should_create_cpf() {
        // given
        String input = "316.965.980-44";

        // then
        Assertions.assertDoesNotThrow(() -> {
            // when
            new CPF(input);
        });
    }

    @Test
    void given_a_invalid_input_when_creating_a_cpf_then_should_not_create_cpf() {
        // given
        String[] inputs = {
            "11111111111",
            "12345678910",
        };

        // then
        for (String input : inputs) {
            Assertions.assertThrows(InvalidCPFException.class, () -> {
                // when
                new CPF(input);
            });
        }
    }

    @Test
    void given_a_invalid_input_length_when_creating_a_cpf_then_should_not_create_cpf() {
        // given
        String input = "316.965.980-4";

        // then
        Assertions.assertThrows(InvalidCPFLenghtException.class, () -> {
            // when
            new CPF(input);
        });
    }

    @Test
    void given_a_null_cpf_when_creating_a_cpf_then_should_not_create_cpf() {
        // given
        String input = null;

        // then
        Assertions.assertThrows(NullPointerException.class, () -> {
            // when
            new CPF(input);
        });
    }
}

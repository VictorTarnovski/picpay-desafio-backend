package com.picpay.auth.domain.value_objects;

import com.picpay.auth.domain.exceptions.InvalidCNPJException;
import com.picpay.auth.domain.exceptions.InvalidCNPJLenghtException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CNPJTest {
    @Test
    public void given_a_valid_input_when_creating_a_cnpj_then_should_create_cnpj() {
        // given
        String input = "13.423.770/0001-99";

        // then
        Assertions.assertDoesNotThrow(() -> {
            // when
            new CNPJ(input);
        });
    }

    @Test
    public void given_a_invalid_input_when_creating_a_cnpj_then_should_not_create_cnpj() {
        // given
        String[] inputs = {
            "11111111111111",
            "12345678910111",
        };

        // then
        for (String input : inputs) {
            Assertions.assertThrows(InvalidCNPJException.class, () -> {
                // when
                new CNPJ(input);
            });
        }
    }

    @Test
    public void given_a_invalid_input_length_when_creating_a_cnpj_then_should_not_create_cnpj() {
        // given
        String input = "13.423.770/0001-9";

        // then
        Assertions.assertThrows(InvalidCNPJLenghtException.class, () -> {
            // when
            new CNPJ(input);
        });
    }

    @Test
    public void given_a_null_cnpj_when_creating_a_cnpj_then_should_not_create_cnpj() {
        // given
        String input = null;

        // then
        Assertions.assertThrows(NullPointerException.class, () -> {
            // when
            new CNPJ(input);
        });
    }
}

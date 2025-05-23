package com.picpay.auth.domain.value_objects;


import com.github.javafaker.Faker;
import com.picpay.auth.domain.value_objects.Email;
import com.picpay.auth.domain.exceptions.InvalidEmailFormatException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class EmailTest {
    @Test
    public void given_a_valid_input_when_creating_a_email_then_should_create_email() {
        var faker = new Faker();
        // given
        String input = faker.internet().emailAddress();

        // then
        Assertions.assertDoesNotThrow(() -> {
            // when
            new Email(input);
        });
    }

    @Test
    public void given_a_invalid_input_when_creating_a_email_then_should_not_create_email() {
        var faker = new Faker();
        // given
        String input = faker.bothify("???##??###");

        // then
        Assertions.assertThrows(InvalidEmailFormatException.class, () -> {
            // when
            new Email(input);
        });
    }

    @Test
    public void given_a_null_email_when_creating_a_email_then_should_not_create_email() {
        // given
        String input = null;

        // then
        Assertions.assertThrows(NullPointerException.class, () -> {
            // when
            new Email(input);
        });
    }
}

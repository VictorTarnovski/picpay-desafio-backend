package com.picpay.transfers.domain.value_objects;


import com.github.javafaker.Faker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class EmailTest {
    @Test
    public void given_a_valid_email_when_creating_a_email_then_should_create_email() {
        var faker = new Faker();
        // given
        String email = faker.internet().emailAddress();

        // then
        Assertions.assertDoesNotThrow(() -> {
            // when
            new Email(email);
        });
    }

    @Test
    public void given_a_invalid_email_when_creating_a_email_then_should_not_create_email() {
        var faker = new Faker();
        // given
        String email = faker.bothify("???##??###");

        // then
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            // when
            new Email(email);
        });
    }

    @Test
    public void given_a_null_email_when_creating_a_email_then_should_not_create_email() {
        // given
        String email = null;

        // then
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            // when
            new Email(email);
        });
    }
}

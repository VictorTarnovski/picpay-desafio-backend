package com.picpay.shared.domain.value_objects;

import org.junit.jupiter.api.Test;

import java.util.Currency;

import static org.junit.jupiter.api.Assertions.*;

class MoneyTest {
    @Test
    void given_a_valid_currency_and_amount_when_creating_money_then_should_create() {
        Money money = new Money(0, Currency.getInstance("BRL"));
        assertEquals(0, money.amount());
        assertEquals(Currency.getInstance("BRL"), money.currency());
    }

    @Test
    void given_two_money_instances_when_adding_then_should_return_correct_sum() {
        Money m1 = Money.Real(50);
        Money m2 = Money.Real(20);

        Money result = m1.add(m2);
        assertEquals(70, result.amount());
    }

    @Test
    void given_two_money_instances_when_subtracting_then_should_return_correct_difference() {
        Money m1 = Money.Real(100);
        Money m2 = Money.Real(30);

        Money result = m1.subtract(m2);
        assertEquals(70, result.amount());
    }

    @Test
    void given_two_equal_money_instances_when_checking_equality_then_should_return_true() {
        Money m1 = Money.Real(100);
        Money m2 = Money.Real(100);

        assertEquals(m1, m2);
    }

    @Test
    void given_two_different_money_instances_when_checking_equality_then_should_return_false() {
        Money m1 = Money.Real(100);
        Money m2 = Money.Real(90);

        assertNotEquals(m1, m2);
    }

    @Test
    void given_two_equal_money_instances_when_calling_greaterThan_then_should_return_false() {
        Money m1 = Money.Real(100);
        Money m2 = Money.Real(100);

        assertFalse(m1.greaterThan(m2));
    }

    @Test
    void given_two_different_money_instances_when_calling_greaterThan_then_should_return_true() {
        Money m1 = Money.Real(100);
        Money m2 = Money.Real(50);

        assertTrue(m1.greaterThan(m2));
    }

    @Test
    void given_two_different_money_instances_when_calling_greaterThanOrEqual_then_should_return_true() {
        Money m1 = Money.Real(100);
        Money m2 = Money.Real(50);

        assertTrue(m1.greaterThanOrEqual(m2));
    }

    @Test
    void given_two_equal_money_instances_when_calling_greaterThanOrEqual_then_should_return_true() {
        Money m1 = Money.Real(100);
        Money m2 = Money.Real(100);

        assertTrue(m1.greaterThanOrEqual(m2));
    }

    @Test
    void given_two_different_money_instances_when_calling_greaterThanOrEqual_then_should_return_false() {
        Money m1 = Money.Real(50);
        Money m2 = Money.Real(100);

        assertFalse(m1.greaterThanOrEqual(m2));
    }

    @Test
    void given_two_equal_money_instances_when_calling_lessThan_then_should_return_false() {
        Money m1 = Money.Real(100);
        Money m2 = Money.Real(100);

        assertFalse(m1.lessThan(m2));
    }

    @Test
    void given_two_different_money_instances_when_calling_lessThan_then_should_return_true() {
        Money m1 = Money.Real(50);
        Money m2 = Money.Real(100);

        assertTrue(m1.lessThan(m2));
    }

    @Test
    void given_two_different_money_instances_when_calling_lessThanOrEqual_then_should_return_true() {
        Money m1 = Money.Real(50);
        Money m2 = Money.Real(100);

        assertTrue(m1.lessThanOrEqual(m2));
    }

    @Test
    void given_two_equal_money_instances_when_calling_lessThanOrEqual_then_should_return_true() {
        Money m1 = Money.Real(100);
        Money m2 = Money.Real(100);

        assertTrue(m1.lessThanOrEqual(m2));
    }

    @Test
    void given_two_different_money_instances_when_calling_lessThanOrEqual_then_should_return_false() {
        Money m1 = Money.Real(100);
        Money m2 = Money.Real(50);

        assertFalse(m1.lessThanOrEqual(m2));
    }

}

package com.ironhack.BankingSystem.Model.Utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static com.ironhack.BankingSystem.Model.Utils.AgeCalculator.calculateAge;
import static org.junit.jupiter.api.Assertions.*;

class MoneyTest {

    private Money balance;
    private Money amount;
    private Money amount1;

    @BeforeEach
    public void setUp() {
        balance= new Money(new BigDecimal(1234.50));
        amount= new Money(new BigDecimal(1000));
        amount1= new Money(new BigDecimal(10000));
    }
    @Test
    @DisplayName("increase balance amount")
    void testIncreaseAmount() {
        assertEquals(1340.50, balance.increaseAmount(amount) );
        assertEquals(12400.50, balance.increaseAmount(amount1) );
    }



    @Test
    @DisplayName("decrease balance amount")
    void testDecreaseAmount() {
        assertEquals(234.5, balance.increaseAmount(amount) );
        assertEquals(-8765.5, balance.increaseAmount(amount1) );
    }


}
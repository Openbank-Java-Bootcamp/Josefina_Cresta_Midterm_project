package com.ironhack.BankingSystem.Model.Utils;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static com.ironhack.BankingSystem.Model.Utils.AgeCalculator.calculateAge;
import static org.junit.jupiter.api.Assertions.*;

class AgeCalculatorTest {

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDateTime birthDate;
    private LocalDateTime today;
    private int age;

    @BeforeEach
    public void setUp() {
        LocalDateTime today = LocalDateTime.now();
    }

    @Test
    @DisplayName("calculate the age younger than 24")
    public void calculateAge_young24() {
        LocalDateTime birthday = LocalDateTime.parse("2000-01-13");
        assertEquals(22, calculateAge(birthday, today));
    }

    @Test
    @DisplayName("calculate the age older than 24")
    public void calculateAge_older24() {
        LocalDateTime birthday = LocalDateTime.parse("1997-01-13");
        assertEquals(25, calculateAge(birthday, today));
    }

}
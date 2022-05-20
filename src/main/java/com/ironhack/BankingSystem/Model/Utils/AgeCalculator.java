package com.ironhack.BankingSystem.Model.Utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

public class AgeCalculator {

    public static int calculateAge(LocalDateTime birthDate, LocalDateTime currentDate) {
        System.out.println(birthDate);
        System.out.println(currentDate);
        if ((birthDate != null) && (currentDate != null)) {
            return Period.between(LocalDate.from(birthDate), LocalDate.from(currentDate)).getYears();
        } else {
            return 0;
        }
    }
}
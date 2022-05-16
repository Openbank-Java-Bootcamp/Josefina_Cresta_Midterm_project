package com.ironhack.BankingSystem.Model.Accounts;

import com.ironhack.BankingSystem.Model.Accounts.Account;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Savings extends Account {

    private double interestRate;

}

package com.ironhack.BankingSystem.Model.Accounts;

import com.ironhack.BankingSystem.Model.Accounts.Account;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
//@Table(name = "Savings")
public class Savings extends Account {

    @Column(name = "interestRate")
    private BigDecimal interestRate;

}

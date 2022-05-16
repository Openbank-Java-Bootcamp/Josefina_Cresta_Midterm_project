package com.ironhack.BankingSystem.Model.Accounts;


import com.ironhack.BankingSystem.Model.Accounts.Account;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import org.javamoney.moneta.Money;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
//@Table(name = "CreditCard")
public class CreditCard extends Account {

    @Column(length = 510)
    private Money creditLimit;

    @Column(name = "interestRate")
    private BigDecimal interestRate;
}

package com.ironhack.BankingSystem.Model.Accounts;


import com.ironhack.BankingSystem.Enum.Status;
import com.ironhack.BankingSystem.Model.Accounts.Account;
import com.ironhack.BankingSystem.Model.Users.AccountHolder;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

import org.javamoney.moneta.Money;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
//@Table(name = "CreditCard")
public class CreditCard extends Account {

    @AttributeOverrides({
            @AttributeOverride(name = "currency", column = @Column(name = "creditLimit_currency")),
            @AttributeOverride(name = "amount", column = @Column(name = "creditLimit_amount"))
    })
    private Money creditLimit;

    @Column(name = "interestRate")
    private BigDecimal interestRate;


}

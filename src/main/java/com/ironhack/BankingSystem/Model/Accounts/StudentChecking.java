package com.ironhack.BankingSystem.Model.Accounts;

import com.ironhack.BankingSystem.Enum.Status;
import com.ironhack.BankingSystem.Model.Accounts.Account;
import com.ironhack.BankingSystem.Model.Users.AccountHolder;
import com.ironhack.BankingSystem.Model.Utils.Money;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
//@Table(name = "StudentChecking")
public class StudentChecking extends Account {

    public StudentChecking(Money balance, String secretKey, AccountHolder primaryOwner, String secondaryOwner) {
        super(balance, secretKey,  primaryOwner, secondaryOwner);
    }
}

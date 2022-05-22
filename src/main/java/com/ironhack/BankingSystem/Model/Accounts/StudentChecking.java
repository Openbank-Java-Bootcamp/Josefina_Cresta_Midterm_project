package com.ironhack.BankingSystem.Model.Accounts;
import com.ironhack.BankingSystem.Model.Users.AccountHolder;
import com.ironhack.BankingSystem.Model.Utils.Money;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
//@Table(name = "StudentChecking")
public class StudentChecking extends Account {

    public StudentChecking(Money balance, String secretKey, AccountHolder primaryOwner, String secondaryOwner) {
        super(balance, secretKey,  primaryOwner, secondaryOwner);
    }
}

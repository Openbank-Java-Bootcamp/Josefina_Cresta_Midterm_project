package com.ironhack.BankingSystem.Model.Accounts;

import com.ironhack.BankingSystem.Enum.Status;
import com.ironhack.BankingSystem.Model.Accounts.Account;
import com.ironhack.BankingSystem.Model.Users.AccountHolder;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
//@Table(name = "StudentChecking")
public class StudentChecking extends Account {

    /*public StudentChecking(Long AccountId, double balance, AccountHolder primaryOwner, String secondaryOwner, double penaltyFee, String secretKey, double minimumBalance, Date creationDate, Status status, AccountHolder accountHolder) {
        super(AccountId, balance, primaryOwner, secondaryOwner, penaltyFee, secretKey, minimumBalance, creationDate, status, accountHolder);
    }

    public StudentChecking() {
    }*/
}

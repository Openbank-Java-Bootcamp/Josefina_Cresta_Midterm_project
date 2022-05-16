package com.ironhack.BankingSystem.Model.Accounts;

import com.ironhack.BankingSystem.Model.Accounts.Account;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CheckingAccounts extends Account {

    private double minimumBalance;

    private double monthlyMaintenanceFee;


}

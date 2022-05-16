package com.ironhack.BankingSystem.Model.Accounts;

import com.ironhack.BankingSystem.Model.Accounts.Account;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.javamoney.moneta.Money;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
//@Table(name = "CheckingAccounts")
public class CheckingAccounts extends Account {

    @Column(length = 510)
    private Money minimumBalance;

    @Column(length = 510)
    private Money monthlyMaintenanceFee;


}

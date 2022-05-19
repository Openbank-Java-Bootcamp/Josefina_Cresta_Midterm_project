package com.ironhack.BankingSystem.Model.Accounts;

import com.ironhack.BankingSystem.Model.Accounts.Account;
import com.ironhack.BankingSystem.Model.Utils.Money;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
//@Table(name = "CheckingAccounts")
public class CheckingAccounts extends Account {

    @AttributeOverrides({
            @AttributeOverride(name = "currency", column = @Column(name = "minimumBalance_currency")),
            @AttributeOverride(name = "amount", column = @Column(name = "minimumBalance_amount"))
    })
    @Embedded
    private Money minimumBalance;

    @AttributeOverrides({
            @AttributeOverride(name = "currency", column = @Column(name = "monthlyMaintenanceFee_currency")),
            @AttributeOverride(name = "amount", column = @Column(name = "monthlyMaintenanceFee_amount"))
    })
    @Embedded
    private Money monthlyMaintenanceFee;


}

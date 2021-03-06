package com.ironhack.BankingSystem.Model.Accounts;

import com.ironhack.BankingSystem.Model.Users.AccountHolder;
import com.ironhack.BankingSystem.Model.Utils.Money;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


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
    private Money minimumBalance=new Money(new BigDecimal(250));

    @AttributeOverrides({
            @AttributeOverride(name = "currency", column = @Column(name = "monthlyMaintenanceFee_currency")),
            @AttributeOverride(name = "amount", column = @Column(name = "monthlyMaintenanceFee_amount"))
    })
    @Embedded
    private Money monthlyMaintenanceFee = new Money(new BigDecimal(12));;


    public CheckingAccounts(Money balance, String secretKey, AccountHolder primaryOwner, String secondaryOwner) {
        super(balance, secretKey,  primaryOwner, secondaryOwner);
        this.minimumBalance = minimumBalance;
        this.monthlyMaintenanceFee = monthlyMaintenanceFee;
    }
}

package com.ironhack.BankingSystem.Model.Accounts;


import com.ironhack.BankingSystem.Enum.Status;
import com.ironhack.BankingSystem.Model.Accounts.Account;
import com.ironhack.BankingSystem.Model.Users.AccountHolder;
import com.ironhack.BankingSystem.Model.Utils.Money;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
//@Table(name = "CreditCard")
public class CreditCard extends Account {

    @Column(name = "interestRate")
    private BigDecimal interestRate;

    @AttributeOverrides({
            @AttributeOverride(name = "currency", column = @Column(name = "credit_Limit_currency")),
            @AttributeOverride(name = "amount", column = @Column(name = "credit_Limit_amount"))
    })
    @Embedded
    private Money creditLimit;

    private final BigDecimal MAX_CREDIT_LIMIT = BigDecimal.valueOf(100000);

    @AttributeOverrides({
            @AttributeOverride(name = "currency", column = @Column(name = "default_CreditLimit_currency")),
            @AttributeOverride(name = "amount", column = @Column(name = "default_CreditLimit_amount"))
    })
    @Embedded
    private Money defaultCreditLimit = new Money(MAX_CREDIT_LIMIT);


    public CreditCard(Money balance, String secretKey, AccountHolder primaryOwner, String secondaryOwner,
                      Money creditLimit, BigDecimal interestRate) {
        super(balance, secretKey, primaryOwner, secondaryOwner);
        this.creditLimit = defaultCreditLimit;
        this.interestRate = BigDecimal.valueOf(0.2);
    }

    public void setCreditLimit(Money creditLimit){
        if ( creditLimit.getAmount().compareTo(BigDecimal.valueOf(100))==-1 ||
                creditLimit.getAmount().compareTo(BigDecimal.valueOf(100000))==1 ){
            throw new IllegalArgumentException("Credit Limit must be between 100 and 1000, inclusive.");
        } else {
            this.creditLimit =  creditLimit;
        }
    }

    public void setInterestRate(BigDecimal interestRate){
        if ( interestRate.compareTo(BigDecimal.valueOf(0.1))==-1 ||
                interestRate.compareTo(BigDecimal.valueOf(0.2))==1 ){
            throw new IllegalArgumentException("Interest Rate must be between 0.1 and 0.2");
        } else {
            this.interestRate =  interestRate;
        }
    }


}

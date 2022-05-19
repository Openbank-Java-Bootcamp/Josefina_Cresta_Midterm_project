package com.ironhack.BankingSystem.Model.Accounts;

import com.ironhack.BankingSystem.Enum.Status;
import com.ironhack.BankingSystem.Model.Users.AccountHolder;
import com.ironhack.BankingSystem.Model.Utils.Money;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
//@Table(name = "Savings")
public class Savings extends Account {

    @Column(name = "interestRate")
    private BigDecimal interestRate;

    @AttributeOverrides({
            @AttributeOverride(name = "currency", column = @Column(name = "Savings_minimumBalance_currency")),
            @AttributeOverride(name = "amount", column = @Column(name = "Savings_minimumBalance_amount"))
    })
    @Embedded
    private Money minimumBalance;

    private final BigDecimal MAX_MINIMUM_BALANCE = BigDecimal.valueOf(1000);

    @AttributeOverrides({
            @AttributeOverride(name = "currency", column = @Column(name = "default_minimumBalance_currency")),
            @AttributeOverride(name = "amount", column = @Column(name = "default_minimumBalance_amount"))
    })
   @Embedded
    private Money defaultMinimumBalance = new Money(MAX_MINIMUM_BALANCE);

    public Savings(Money balance, String secretKey, Money penaltyFee, Status status,
                   AccountHolder primaryOwner, String secondaryOwner) {
        super(balance, secretKey, penaltyFee, status, primaryOwner, secondaryOwner);
        this.interestRate = BigDecimal.valueOf(0.0025);
        this.minimumBalance = defaultMinimumBalance;
    }

    public Savings(Money balance, String secretKey, Money penaltyFee, Status status,
                   AccountHolder primaryOwner, String secondaryOwner) {
        super(balance, secretKey, penaltyFee, status, primaryOwner, secondaryOwner);
        this.interestRate = BigDecimal.valueOf(0.0025);
        this.minimumBalance = defaultMinimumBalance;
    }

    public void setMinimumBalance(Money minimumBalance){
        if ( minimumBalance.getAmount().compareTo(BigDecimal.valueOf(100))==-1 ||
                minimumBalance.getAmount().compareTo(BigDecimal.valueOf(1000))==1 ){
            throw new IllegalArgumentException("Minimum Balance must be between 100 and 1000, inclusive.");
        } else {
            this.minimumBalance =  minimumBalance;
        }
    }




}

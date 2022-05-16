package com.ironhack.BankingSystem.Model.Accounts;

import com.ironhack.BankingSystem.Enum.Status;
import com.ironhack.BankingSystem.Model.Users.AccountHolder;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long AccountId;

    private double balance;

    @ManyToOne
    @JoinColumn(name = "account_holder_id")
    private AccountHolder primaryOwner;

   /* @ManyToOne
    @JoinColumn(name = "account_holder_id")*/
    private String secondaryOwner;

    private double penaltyFee;

    //La Credit tiene las siguientes tambien?
    private String secretKey;

    private double minimumBalance;

    private Date creationDate;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "account_holder_account_holder_id")
    private AccountHolder accountHolder;

    public AccountHolder getAccountHolder() {
        return accountHolder;
    }

    public void setAccountHolder(AccountHolder accountHolder) {
        this.accountHolder = accountHolder;
    }
}

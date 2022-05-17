package com.ironhack.BankingSystem.Model.Accounts;

import com.ironhack.BankingSystem.Enum.Status;
import com.ironhack.BankingSystem.Model.Users.AccountHolder;
import com.ironhack.BankingSystem.Model.Utils.Money;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)      //SI pongo este quitar el generatedValue
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long AccountId;

   /* @AttributeOverrides({
            @AttributeOverride(name = "currency", column = @Column(name = "balance_currency")),
            @AttributeOverride(name = "amount", column = @Column(name = "balance_amount"))
    })*/
    private Money balance;

    //La Credit tiene las siguientes tambien?
    private String secretKey;

    @AttributeOverrides({
            @AttributeOverride(name = "currency", column = @Column(name = "penaltyFee_currency")),
            @AttributeOverride(name = "amount", column = @Column(name = "penaltyFee_amount"))
    })
    private Money penaltyFee;

 /*   @AttributeOverrides({
            @AttributeOverride(name = "currency", column = @Column(name = "minimumBalance_currency")),
            @AttributeOverride(name = "amount", column = @Column(name = "minimumBalance_amount"))
    })
    private Money minimumBalance;
*/
    private LocalDateTime creationDate;

    @Enumerated(EnumType.STRING)
    private Status status;

   /* @ManyToOne
    @JoinColumn(name = "account_holder_id")
    private AccountHolder primaryOwner;*/

   /* @ManyToOne
    @JoinColumn(name = "primaryOwner")
    private AccountHolder primaryOwner;*/

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "primaryOwner")
    private AccountHolder primaryOwner;


    /*  @ManyToOne
      @JoinColumn(name = "account_holder_id")*/
    private String secondaryOwner;

    public Account(Money balance, String secretKey, Money penaltyFee,
                   LocalDateTime creationDate, Status status, AccountHolder primaryOwner, String secondaryOwner) {
        this.balance = balance;
        this.secretKey = secretKey;
        this.penaltyFee = penaltyFee;
        //this.minimumBalance = minimumBalance;
        this.creationDate = creationDate;
        this.status = status;
        this.primaryOwner = primaryOwner;
        this.secondaryOwner = secondaryOwner;
    }
}


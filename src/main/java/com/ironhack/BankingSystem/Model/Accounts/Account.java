package com.ironhack.BankingSystem.Model.Accounts;

import com.ironhack.BankingSystem.Enum.Status;
import com.ironhack.BankingSystem.Model.Users.AccountHolder;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.javamoney.moneta.Money;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long AccountId;

    @Column(length = 510)
    private Money balance;

    //La Credit tiene las siguientes tambien?
    private String secretKey;

    @Column(length = 510)
    private Money penaltyFee;

    @Column(length = 510)
    private Money minimumBalance;

    private Date creationDate;

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


}

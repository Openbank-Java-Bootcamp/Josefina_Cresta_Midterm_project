package com.ironhack.BankingSystem.Model.Accounts;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.ironhack.BankingSystem.Enum.Status;
import com.ironhack.BankingSystem.Model.Users.AccountHolder;
import com.ironhack.BankingSystem.Model.Utils.Money;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static jakarta.persistence.FetchType.EAGER;

@Entity
@Data
@AllArgsConstructor
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)      //SI pongo este quitar el generatedValue
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "account")
public class Account{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long AccountId;

   @AttributeOverrides({
            @AttributeOverride(name = "currency", column = @Column(name = "balance_currency")),
            @AttributeOverride(name = "amount", column = @Column(name = "balance_amount"))
    })
   @Embedded
    private Money balance;

    private String secretKey;
    @AttributeOverrides({
            @AttributeOverride(name = "currency", column = @Column(name = "penaltyFee_currency")),
            @AttributeOverride(name = "amount", column = @Column(name = "penaltyFee_amount"))
    })

    @Embedded
    private Money penaltyFee;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime creationDate;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne(fetch = EAGER)
    @JoinColumn(name = "account_holder_id")
    private AccountHolder primaryOwner;

    private String secondaryOwner;

    private BigDecimal penaltyFee_BD = new BigDecimal(40);
    @Embedded
    private Money penaltyFee_money = new Money(penaltyFee_BD);


    public Account() {
    }

    public Account(Money balance, String secretKey, AccountHolder primaryOwner, String secondaryOwner) {
        this.balance = balance;
        this.secretKey = secretKey;

        this.primaryOwner = primaryOwner;
        this.secondaryOwner = secondaryOwner;

        this.creationDate = LocalDateTime.now();
        this.penaltyFee = penaltyFee_money;
        this.status = Status.ACTIVE;
    }


}


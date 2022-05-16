package com.ironhack.BankingSystem.Model.Users;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ironhack.BankingSystem.Model.Accounts.Account;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.Set;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "account_holder")
public class AccountHolder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountHolderId;

    private String name;
    private Date birthDate;

    @Embedded
    @Column(name = "primary_address")
    private Address primaryAddress;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "streetAddress", column = @Column(name = "mailing_address_street")),
            @AttributeOverride(name = "city", column = @Column(name = "mailing_address_city")),
            @AttributeOverride(name = "country", column = @Column(name = "mailing_address_country")),
            @AttributeOverride(name = "postalCode", column = @Column(name = "mailing_address_postalCode"))
    })
    @Column(name = "mailing_address")
    private Address mailingAddress;


    @OneToMany(mappedBy = "primaryOwner")
    @JsonIgnore
    private List<Account> accountList;


}

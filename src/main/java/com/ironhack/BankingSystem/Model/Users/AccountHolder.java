package com.ironhack.BankingSystem.Model.Users;

import com.ironhack.BankingSystem.Model.Accounts.Account;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
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

    private Date birthDate;

    @Embedded
    @Column(name = "primary_address")
    private Address primaryAddress;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "street", column = @Column(name = "mailing_address_street")),
            @AttributeOverride(name = "city", column = @Column(name = "mailing_address_city")),
            @AttributeOverride(name = "country", column = @Column(name = "mailing_address_country")),
            @AttributeOverride(name = "postalCode", column = @Column(name = "mailing_address_postalCode"))
    })
    @Column(name = "mailing_address")
    private Address mailingAddress;

    @OneToMany(mappedBy = "accountHolder")
    private Set<Account> accounts;


}

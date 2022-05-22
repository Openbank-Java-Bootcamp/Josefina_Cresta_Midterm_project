package com.ironhack.BankingSystem.Model.Users;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.ironhack.BankingSystem.Model.Accounts.Account;
import com.ironhack.BankingSystem.Model.secutiry.Role;
import com.ironhack.BankingSystem.Model.secutiry.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
//@Table(name = "account_holder")
public class AccountHolder extends User {

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDateTime birthDate;

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

    public AccountHolder(String name) {
        super(name);
    }

    public AccountHolder(String name, String username, String password, Collection<Role> roles) {
        super(name, username, password, roles);
    }

    public AccountHolder(Long id, String name, String username, String password, Collection<Role> roles,
                         LocalDateTime birthDate, Address primaryAddress, Address mailingAddress, List<Account> accountList)
    {
        super(id, name, username, password, roles);
        this.birthDate = birthDate;
        this.primaryAddress = primaryAddress;
        this.mailingAddress = mailingAddress;
        this.accountList = accountList;
    }
}

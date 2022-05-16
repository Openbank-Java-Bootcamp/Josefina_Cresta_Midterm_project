package com.ironhack.BankingSystem.Repository;

import com.ironhack.BankingSystem.Model.Accounts.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;


public interface UserRepository extends JpaRepository<Account, Long> {
    Account finnBycreationDate(Date creationDate);
}
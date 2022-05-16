package com.ironhack.BankingSystem.Repository;

import com.ironhack.BankingSystem.Model.Accounts.Account;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Date;


public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findBycreationDate(Date creationDate);
}
package com.ironhack.BankingSystem.Repository.Accounts;

import com.ironhack.BankingSystem.Model.Accounts.Account;
import com.ironhack.BankingSystem.Model.secutiry.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.Date;


@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    /*Role findByUsernamePrimaryOwner(String username);*/
}
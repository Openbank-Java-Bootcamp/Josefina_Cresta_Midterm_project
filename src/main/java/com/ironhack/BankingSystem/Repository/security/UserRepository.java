package com.ironhack.BankingSystem.Repository.security;

import com.ironhack.BankingSystem.Model.Accounts.Account;
import com.ironhack.BankingSystem.Model.secutiry.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;


public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
}
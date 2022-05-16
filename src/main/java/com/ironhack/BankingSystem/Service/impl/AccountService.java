package com.ironhack.BankingSystem.Service.impl;

import com.ironhack.BankingSystem.Repository.Accounts.AccountRepository;
import com.ironhack.BankingSystem.Service.interfaces.AccountServiceInterface;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountService implements AccountServiceInterface {

    @Autowired
    private AccountRepository accountRepository;

    //ACa van los metodos que luego llama el controlador

}

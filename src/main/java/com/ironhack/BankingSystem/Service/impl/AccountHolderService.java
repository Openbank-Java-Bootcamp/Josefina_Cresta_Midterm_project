package com.ironhack.BankingSystem.Service.impl;

import com.ironhack.BankingSystem.Repository.Users.AccountHolderRepository;
import com.ironhack.BankingSystem.Service.interfaces.AccountHolderServiceInterface;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountHolderService implements AccountHolderServiceInterface {

    @Autowired
    private AccountHolderRepository accountHolderRepository;


    //y aca iran los metodos para la third party
}

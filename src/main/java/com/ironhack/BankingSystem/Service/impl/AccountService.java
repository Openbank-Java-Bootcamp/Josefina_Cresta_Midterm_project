package com.ironhack.BankingSystem.Service.impl;

import com.ironhack.BankingSystem.Model.Accounts.Account;
import com.ironhack.BankingSystem.Model.Users.AccountHolder;
import com.ironhack.BankingSystem.Repository.Accounts.AccountRepository;
import com.ironhack.BankingSystem.Repository.Users.AccountHolderRepository;
import com.ironhack.BankingSystem.Service.interfaces.AccountServiceInterface;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountService implements AccountServiceInterface {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountHolderRepository accountHolderRepository;

    @Override
    public Account saveNewAccount(Account account) {
        Optional<AccountHolder> accountOwner = accountHolderRepository.findById(account.getPrimaryOwner().getId());
        if(accountOwner.isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "No Account Holder found with ID passed for this new Account");
        }
        try {
            return accountRepository.save(new Account(
                    account.getBalance(),
                    account.getSecretKey(),
                    account.getPenaltyFee(),
                    account.getCreationDate(),
                    account.getStatus(),
                    account.getPrimaryOwner(),
                    account.getSecondaryOwner()
            ));
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Malformed Account");
        }
    }

    //ACa van los metodos que luego llama el controlador

}

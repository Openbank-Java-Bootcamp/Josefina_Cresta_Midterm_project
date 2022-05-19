package com.ironhack.BankingSystem.Service.impl;

import com.ironhack.BankingSystem.Model.Accounts.Account;
import com.ironhack.BankingSystem.Model.Users.AccountHolder;
import com.ironhack.BankingSystem.Repository.Accounts.AccountRepository;
import com.ironhack.BankingSystem.Repository.Users.AccountHolderRepository;
import com.ironhack.BankingSystem.Service.interfaces.AccountHolderServiceInterface;
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
public class AccountHolderService implements AccountHolderServiceInterface {

    @Autowired
    private AccountHolderRepository accountHolderRepository;

    @Autowired
    private AccountRepository accountRepository;


    //y aca iran los metodos para la third party

    public AccountHolder getAccountHolderById(Long id) {
        Optional<AccountHolder> foundAccountHolder = accountHolderRepository.findById(id);
        if(foundAccountHolder.isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No AccountHolder found with that ID");
        } else {
            return foundAccountHolder.get();
        }
    }

    public void saveAccountHolder(AccountHolder accountHolder) {
        try {
            accountHolderRepository.save(new AccountHolder(accountHolder.getName()));
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Malformed AccountHolder");
        }
    }

    public void updateAccountHolder(Long id, AccountHolder AccountHolder) {
        Optional<AccountHolder> foundAccountHolder = accountHolderRepository.findById(id);
        if(foundAccountHolder.isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No AccountHolder found with that ID");
        } else {
            if(!AccountHolder.getName().isEmpty()){
                foundAccountHolder.get().setName(AccountHolder.getName());
                accountHolderRepository.save(foundAccountHolder.get());
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "AccountHolder Name cannot be blank");
            }
        }
    }

    public void deleteAccountHolder(Long id) {
        Optional<AccountHolder> foundAccountHolder = accountHolderRepository.findById(id);
        if(foundAccountHolder.isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No AccountHolder found with that ID");
        } else {
            accountHolderRepository.delete(foundAccountHolder.get());
        }
    }

/*    @Override
    public AccountHolder getBalance(Long accountId) {
        Account accountFromDB = accountRepository.findById(accountId).orElseThrow(()
                -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found"));
        //if ese account id no esta en su lista de account no puede verlos
        log.info("Changing balance of {}'s account", accountFromDB.getPrimaryOwner().getName());
        accountFromDB.getBalance();
    }*/
}

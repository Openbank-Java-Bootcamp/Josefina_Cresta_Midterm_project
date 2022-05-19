package com.ironhack.BankingSystem.Service.impl;

import com.ironhack.BankingSystem.Model.Accounts.Account;
import com.ironhack.BankingSystem.Model.Accounts.Savings;
import com.ironhack.BankingSystem.Model.Users.AccountHolder;
import com.ironhack.BankingSystem.Model.Utils.Money;
import com.ironhack.BankingSystem.Repository.Accounts.AccountRepository;
import com.ironhack.BankingSystem.Repository.Accounts.SavingsRepository;
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
    private SavingsRepository savingsRepository;


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
                    account.getStatus(),
                    account.getPrimaryOwner(),
                    account.getSecondaryOwner()
            ));
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Malformed Account");
        }
    }



    @Override
    public Savings saveNewSavingsAccount(Savings savings) {
        Optional<AccountHolder> accountOwner = accountHolderRepository.findById(savings.getPrimaryOwner().getId());
        if(accountOwner.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "No Account Holder found with ID passed for this new Account");
        }
        System.out.println(accountOwner.get().getBirthDate());
        try {
            return savingsRepository.save(new Savings(
                    savings.getBalance(),
                    savings.getSecretKey(),
                    savings.getPenaltyFee(),
                    savings.getStatus(),
                    savings.getPrimaryOwner(),
                    savings.getSecondaryOwner()
            ));
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Malformed Account");
        }
    }


    public void updateBalance(Long id, Money balance) {
        Account accountFromDB = accountRepository.findById(id).orElseThrow(()
                -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found"));
        log.info("Changing balance of {}'s account", accountFromDB.getPrimaryOwner().getName());
        accountFromDB.setBalance(balance);
        accountRepository.save(accountFromDB);
    }


}

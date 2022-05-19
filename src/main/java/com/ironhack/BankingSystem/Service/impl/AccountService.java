package com.ironhack.BankingSystem.Service.impl;

import com.ironhack.BankingSystem.Model.Accounts.Account;
import com.ironhack.BankingSystem.Model.Accounts.CreditCard;
import com.ironhack.BankingSystem.Model.Accounts.Savings;
import com.ironhack.BankingSystem.Model.Users.AccountHolder;
import com.ironhack.BankingSystem.Model.Utils.Money;
import com.ironhack.BankingSystem.Repository.Accounts.AccountRepository;
import com.ironhack.BankingSystem.Repository.Accounts.CreditCardRepository;
import com.ironhack.BankingSystem.Repository.Accounts.SavingsRepository;
import com.ironhack.BankingSystem.Repository.Users.AccountHolderRepository;
import com.ironhack.BankingSystem.Service.interfaces.AccountServiceInterface;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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
    private CreditCardRepository creditCardRepository;


    @Autowired
    private AccountHolderRepository accountHolderRepository;


    //Funciona pero no guarda account Holder ni le agrega a la lista del account holder la account
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
                    account.getPrimaryOwner(),
                    account.getSecondaryOwner()
            ));
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Malformed Account");
        }
    }



    //SAVING QUE FUNCIONA
    /*@Override
    public Savings saveNewSavingsAccount(Savings savings) {
        Optional<AccountHolder> accountOwner = accountHolderRepository.findById(savings.getPrimaryOwner().getId());
        if(accountOwner.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "No Account Holder found with ID passed for this new Account");
        }

        try {
            return savingsRepository.save(new Savings(
                    savings.getBalance(),
                    savings.getSecretKey(),
                    savings.getPrimaryOwner(),
                    savings.getSecondaryOwner()
            ));
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Malformed SAVINGS Account");
        }
    }*/

    /*@Override
    public Savings saveNewSavingsAccount(Savings savings) {
        Optional<AccountHolder> accountOwner = accountHolderRepository.findById(savings.getPrimaryOwner().getId());
        if(accountOwner.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "No Account Holder found with ID passed for this new Account");
        }
        List <Account> listSavings = new ArrayList<>();
        listSavings.add(savings);
        savings.getPrimaryOwner().setAccountList(listSavings);
        try {
            return savingsRepository.save(savings);
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Malformed SAVINGS Account");
        }
    }*/

    @Override
    public Savings saveNewSavingsAccount(Savings savings) {

        accountHolderRepository.save(savings.getPrimaryOwner());
        AccountHolder primaryOwner = savings.getPrimaryOwner();
        primaryOwner.setAccountList(Collections.singletonList(savings));

        try {
            Savings newSaving = new Savings(
                            savings.getBalance(),
                            savings.getSecretKey(),
                            savings.getPrimaryOwner(),
                            savings.getSecondaryOwner()
                    );
            savingsRepository.save(newSaving);
            newSaving.getPrimaryOwner().setAccountList(Collections.singletonList(newSaving));
            return newSaving;
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Malformed SAVINGS Account");
        }
    }

    public CreditCard saveNewCreditAccount(CreditCard creditCard) {
        Optional<AccountHolder> accountOwner = accountHolderRepository.findById(creditCard.getPrimaryOwner().getId());
        if(accountOwner.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "No Account Holder found with ID passed.Let's create a new client ");
        }

        accountHolderRepository.save(creditCard.getPrimaryOwner());
        AccountHolder primaryOwner = creditCard.getPrimaryOwner();
        primaryOwner.setAccountList(Collections.singletonList(creditCard));

        try {
            return creditCardRepository.save(new CreditCard(
                    creditCard.getBalance(),
                    creditCard.getSecretKey(),
                    creditCard.getPrimaryOwner(),
                    creditCard.getSecondaryOwner(),
                    creditCard.getCreditLimit(),
                    creditCard.getInterestRate()
            ));
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Malformed CREDIT Account");
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

package com.ironhack.BankingSystem.Service.impl;

import com.ironhack.BankingSystem.Model.Accounts.*;
import com.ironhack.BankingSystem.Model.Users.AccountHolder;
import com.ironhack.BankingSystem.Model.Utils.Money;
import com.ironhack.BankingSystem.Repository.Accounts.*;
import com.ironhack.BankingSystem.Repository.Users.AccountHolderRepository;
import com.ironhack.BankingSystem.Service.interfaces.AccountServiceInterface;
import com.ironhack.BankingSystem.Service.interfaces.UserServiceInterface;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;

import static com.ironhack.BankingSystem.Model.Utils.AgeCalculator.calculateAge;

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
    private CheckingAccountsRepository checkingAccountsRepository;

    @Autowired
    private StudentCheckingRepository studentCheckingRepository;

    @Autowired
    private AccountHolderRepository accountHolderRepository;

    @Autowired
    private UserServiceInterface userService;


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

    @Override
    public Savings saveNewSavingsAccount(Savings savings) {
        userService.saveUser(savings.getPrimaryOwner());
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

        userService.saveUser(creditCard.getPrimaryOwner());
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

    @Override
    public Account saveNewCheckingAccount(CheckingAccounts checkingAccount) {
        Optional<AccountHolder> accountOwner = accountHolderRepository.findById(
                checkingAccount.getPrimaryOwner().getId());
        if(accountOwner.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "No Account Holder found with ID passed. Let's create a new client ");
        }
        userService.saveUser(checkingAccount.getPrimaryOwner());
        AccountHolder primaryOwner = checkingAccount.getPrimaryOwner();
        primaryOwner.setAccountList(Collections.singletonList(checkingAccount));

        //Si es menor de 24 que cree una de estudiante
        LocalDateTime birthDate = primaryOwner.getBirthDate();
        int age = calculateAge(birthDate, LocalDateTime.now());

        log.info("The age of the client is: " , age);
        try{
            if  (age < 24) {
            log.info("Let create a student account");
            return studentCheckingRepository.save(new StudentChecking(
                    checkingAccount.getBalance(),
                    checkingAccount.getSecretKey(),
                    checkingAccount.getPrimaryOwner(),
                    checkingAccount.getSecondaryOwner()
            ));
        }else{
            return checkingAccountsRepository.save(new CheckingAccounts(
                    checkingAccount.getBalance(),
                    checkingAccount.getSecretKey(),
                    checkingAccount.getPrimaryOwner(),
                    checkingAccount.getSecondaryOwner()
            ));
        } }catch (Exception e){
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

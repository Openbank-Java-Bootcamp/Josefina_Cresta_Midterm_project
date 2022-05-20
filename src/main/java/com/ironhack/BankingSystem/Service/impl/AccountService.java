package com.ironhack.BankingSystem.Service.impl;

import com.ironhack.BankingSystem.Model.Accounts.*;
import com.ironhack.BankingSystem.Model.Users.AccountHolder;
import com.ironhack.BankingSystem.Model.Utils.AgeCalculator;
import com.ironhack.BankingSystem.Model.Utils.Money;
import com.ironhack.BankingSystem.Repository.Accounts.*;
import com.ironhack.BankingSystem.Repository.Users.AccountHolderRepository;
import com.ironhack.BankingSystem.Repository.security.UserRepository;
import com.ironhack.BankingSystem.Service.interfaces.AccountServiceInterface;
import com.ironhack.BankingSystem.Service.interfaces.UserServiceInterface;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.ironhack.BankingSystem.Service.impl.UserService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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

        //accountHolderRepository.save(savings.getPrimaryOwner());
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
        //accountHolderRepository.save(creditCard.getPrimaryOwner());
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
        Optional<AccountHolder> accountOwner = accountHolderRepository.findById(checkingAccount.getPrimaryOwner().getId());
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

        System.out.println("AGEeeeeeee:: " + age);
        try{
            if  (age < 24) {
            System.out.println("LEt create a student account");
            return studentCheckingRepository.save(new StudentChecking(
                    checkingAccount.getBalance(),
                    checkingAccount.getSecretKey(),
                    checkingAccount.getPrimaryOwner(),
                    checkingAccount.getSecondaryOwner()
            ));
        }else{
                System.out.println("LEt create a simple CHACKING account");
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


    ////////////////////////////////

/*    public static int calculateAge(LocalDateTime birthDate, LocalDateTime currentDate) {
        System.out.println(birthDate);
        System.out.println(currentDate);
        if ((birthDate != null) && (currentDate != null)) {
            return Period.between(LocalDate.from(birthDate), LocalDate.from(currentDate)).getYears();
        } else {
            return 0;
        }
    }*/

    ///////////////////////////////


    public void updateBalance(Long id, Money balance) {
        Account accountFromDB = accountRepository.findById(id).orElseThrow(()
                -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found"));
        log.info("Changing balance of {}'s account", accountFromDB.getPrimaryOwner().getName());
        accountFromDB.setBalance(balance);
        accountRepository.save(accountFromDB);
    }


}

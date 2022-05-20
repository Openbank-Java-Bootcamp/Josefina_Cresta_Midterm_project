package com.ironhack.BankingSystem.Controller.impl;

import com.ironhack.BankingSystem.Controller.interfaces.AccountControllerInterface;
import com.ironhack.BankingSystem.DTO.AccountBalanceOnlyDTO;
import com.ironhack.BankingSystem.Model.Accounts.Account;
import com.ironhack.BankingSystem.Model.Accounts.CheckingAccounts;
import com.ironhack.BankingSystem.Model.Accounts.CreditCard;
import com.ironhack.BankingSystem.Model.Accounts.Savings;
import com.ironhack.BankingSystem.Service.impl.AccountService;
import com.ironhack.BankingSystem.Service.interfaces.AccountServiceInterface;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("bank/accounts")
public class AccountController implements AccountControllerInterface {
    @Autowired
    private AccountServiceInterface accountServiceInterface;

    @Autowired
    private AccountService accountService;


    //ACa van los endpoint

    //General account
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createAccount(@RequestBody @Valid Account account){
        accountServiceInterface.saveNewAccount(account);
    }

    //Savings
    @PostMapping("/newSavings")
    @ResponseStatus(HttpStatus.CREATED)
    public void createSavingsAccount(@RequestBody @Valid Savings savings){
        accountServiceInterface.saveNewSavingsAccount(savings);
    }

    @Override
    @PostMapping("/newCredit")
    @ResponseStatus(HttpStatus.CREATED)
    public void createCreditAccount(@RequestBody @Valid CreditCard creditCard) {
        accountServiceInterface.saveNewCreditAccount(creditCard);
    }

    @Override
    @PostMapping("/newChecking")
    @ResponseStatus(HttpStatus.CREATED)
    public void createCheckingAccount(@RequestBody @Valid CheckingAccounts checkingAccounts) {
        accountServiceInterface.saveNewCheckingAccount(checkingAccounts);
    }


    @PatchMapping("/balance/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void UpdateBalance(@PathVariable Long id, @RequestBody AccountBalanceOnlyDTO newBalance){
        accountService.updateBalance(id, newBalance.getBalance());
    }
}

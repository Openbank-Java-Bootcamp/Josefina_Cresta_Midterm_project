package com.ironhack.BankingSystem.Controller.interfaces;

import com.ironhack.BankingSystem.DTO.AccountBalanceOnlyDTO;
import com.ironhack.BankingSystem.Model.Accounts.Account;
import com.ironhack.BankingSystem.Model.Accounts.CheckingAccounts;
import com.ironhack.BankingSystem.Model.Accounts.CreditCard;
import com.ironhack.BankingSystem.Model.Accounts.Savings;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

public interface AccountControllerInterface {

    public void createAccount(Account account);
    public void createSavingsAccount(Savings savings);
    public void createCreditAccount(CreditCard creditCard);

    public void createCheckingAccount(CheckingAccounts checkingAccounts);

    void UpdateBalance(Long id, AccountBalanceOnlyDTO newBalance);

}

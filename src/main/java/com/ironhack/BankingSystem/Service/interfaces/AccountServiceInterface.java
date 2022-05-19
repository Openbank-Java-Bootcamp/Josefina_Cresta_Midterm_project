package com.ironhack.BankingSystem.Service.interfaces;

import com.ironhack.BankingSystem.Model.Accounts.Account;
import com.ironhack.BankingSystem.Model.Accounts.CreditCard;
import com.ironhack.BankingSystem.Model.Accounts.Savings;
import com.ironhack.BankingSystem.Model.Utils.Money;

public interface AccountServiceInterface {

    //Aca ira las signatures de los metodos que se definen een el service
    public Account saveNewAccount(Account account);


    Savings saveNewSavingsAccount(Savings savings);
    CreditCard saveNewCreditAccount(CreditCard creditCard);

    void updateBalance(Long id, Money balance);

}

package com.ironhack.BankingSystem.Service.interfaces;

import com.ironhack.BankingSystem.Model.Users.AccountHolder;

public interface AccountHolderServiceInterface {

    //Aca ira las signatures de los metodos que se definen een el service
    public AccountHolder getAccountHolderById(Long id);
    public void saveAccountHolder(AccountHolder author);
    public void updateAccountHolder(Long id, AccountHolder author);
    public void deleteAccountHolder(Long id);

    /*AccountHolder getBalance(Long id);*/
}

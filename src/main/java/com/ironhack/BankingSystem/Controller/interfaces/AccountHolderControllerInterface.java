package com.ironhack.BankingSystem.Controller.interfaces;

import com.ironhack.BankingSystem.Model.Users.AccountHolder;

public interface AccountHolderControllerInterface {

    //Aca van las signutere de los metodos que defino en el controller
    public AccountHolder getAccountHolder(Long id);
    public void storeAccountHolder(AccountHolder accountHolder);
    public void updateAccountHolder(Long id, AccountHolder accountHolder);
    public void deleteAccountHolder(Long id);
}

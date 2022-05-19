package com.ironhack.BankingSystem.Controller.interfaces;

import com.ironhack.BankingSystem.DTO.AccountBalanceOnlyDTO;
import com.ironhack.BankingSystem.Model.Accounts.Account;
import com.ironhack.BankingSystem.Model.Accounts.Savings;

public interface AccountControllerInterface {
    //Aca van las signutere de los metodos que defino en el controller
    public void createAccount(Account account);
    public void createSavingsAccount(Savings savings);
    void UpdateBalance(Long id, AccountBalanceOnlyDTO newBalance);

}

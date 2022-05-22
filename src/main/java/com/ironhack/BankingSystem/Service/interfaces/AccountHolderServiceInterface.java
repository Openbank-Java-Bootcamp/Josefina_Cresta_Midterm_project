package com.ironhack.BankingSystem.Service.interfaces;

import com.ironhack.BankingSystem.DTO.TransactionDTO;
import com.ironhack.BankingSystem.Model.Users.AccountHolder;
import com.ironhack.BankingSystem.Model.Utils.Money;

public interface AccountHolderServiceInterface {
    public AccountHolder getAccountHolderById(Long id);
    public void saveAccountHolder(AccountHolder author);
    public void updateAccountHolder(Long id, AccountHolder author);
    public void deleteAccountHolder(Long id);

    Money getBalance(Long accountId, Long id);

    public void transaction(Long targetId, TransactionDTO transactionDTO);

    public void transactionTo(Long accountHolderId, Long targetId, TransactionDTO transactionDTO);


}

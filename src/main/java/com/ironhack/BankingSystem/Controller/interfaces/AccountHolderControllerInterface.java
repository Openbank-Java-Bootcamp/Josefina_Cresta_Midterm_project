package com.ironhack.BankingSystem.Controller.interfaces;

import com.ironhack.BankingSystem.DTO.TransactionDTO;
import com.ironhack.BankingSystem.Model.Users.AccountHolder;
import com.ironhack.BankingSystem.Model.Utils.Money;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

public interface AccountHolderControllerInterface {

    public AccountHolder getAccountHolder(Long id);
    public void storeAccountHolder(AccountHolder accountHolder);
    public void updateAccountHolder(Long id, AccountHolder accountHolder);
    public void deleteAccountHolder(Long id);
    public Money getBalance(Long accountHolderId, Long accountId);
    public void makeTransaction(Long targetId, TransactionDTO transactionDTO);
}

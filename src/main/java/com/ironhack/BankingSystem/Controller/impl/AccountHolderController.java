package com.ironhack.BankingSystem.Controller.impl;

import com.ironhack.BankingSystem.Controller.interfaces.AccountHolderControllerInterface;
import com.ironhack.BankingSystem.DTO.TransactionDTO;
import com.ironhack.BankingSystem.Model.Users.AccountHolder;
import com.ironhack.BankingSystem.Model.Utils.Money;
import com.ironhack.BankingSystem.Repository.Accounts.AccountRepository;
import com.ironhack.BankingSystem.Repository.Users.AccountHolderRepository;
import com.ironhack.BankingSystem.Service.interfaces.AccountHolderServiceInterface;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("bank/accountholders")
public class AccountHolderController implements AccountHolderControllerInterface {
    @Autowired
    private AccountHolderServiceInterface accountHolderServiceInterface;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private AccountHolderRepository accountHolderRepository;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AccountHolder getAccountHolder(@PathVariable Long id) {
        return accountHolderServiceInterface.getAccountHolderById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void storeAccountHolder(@RequestBody @Valid AccountHolder accountHolder) {
        accountHolderServiceInterface.saveAccountHolder(accountHolder);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateAccountHolder(@PathVariable Long id, @RequestBody @Valid AccountHolder accountHolder) {
        accountHolderServiceInterface.updateAccountHolder(id, accountHolder);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAccountHolder(@PathVariable Long id) {
        accountHolderServiceInterface.deleteAccountHolder(id);
    }

    @GetMapping("/balance")
    @ResponseStatus(HttpStatus.OK)
    public Money getBalance(@RequestParam(name = "holder_id") Long accountHolderId,
                            @RequestParam(name = "account_id") Long accountId) {
        return accountHolderServiceInterface.getBalance(accountHolderId, accountId);
    }


    @PatchMapping("/transactions")
    @ResponseStatus(HttpStatus.OK)
    public void makeTransaction(@RequestParam(name = "target_id") Long targetId,
                                @RequestBody @Valid TransactionDTO transactionDTO) {
        accountHolderServiceInterface.transaction(targetId, transactionDTO);
    }

    @PatchMapping("/transactions/accountToAccount")
    @ResponseStatus(HttpStatus.OK)
    public void makeTransaction(@RequestParam(name = "account_id") Long accountHolderId,
                                @RequestParam(name = "target_id") Long targetId,
                                @RequestBody @Valid TransactionDTO transactionDTO) {
        accountHolderServiceInterface.transactionTo(accountHolderId, targetId, transactionDTO);
    }
}

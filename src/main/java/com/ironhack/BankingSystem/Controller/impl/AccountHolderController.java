package com.ironhack.BankingSystem.Controller.impl;

import com.ironhack.BankingSystem.Controller.interfaces.AccountControllerInterface;
import com.ironhack.BankingSystem.Controller.interfaces.AccountHolderControllerInterface;
import com.ironhack.BankingSystem.DTO.TransactionDTO;
import com.ironhack.BankingSystem.Model.Accounts.Account;
import com.ironhack.BankingSystem.Model.Accounts.Savings;
import com.ironhack.BankingSystem.Model.Users.AccountHolder;
import com.ironhack.BankingSystem.Model.Utils.Money;
import com.ironhack.BankingSystem.Repository.Accounts.AccountRepository;
import com.ironhack.BankingSystem.Repository.Users.AccountHolderRepository;
import com.ironhack.BankingSystem.Service.interfaces.AccountHolderServiceInterface;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("bank/accountholders")
public class AccountHolderController implements AccountHolderControllerInterface {
    @Autowired
    private AccountHolderServiceInterface accountHolderServiceInterface;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private AccountHolderRepository accountHolderRepository;

    //ACa van los endpoint
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

    /*@GetMapping("/balance")
    @ResponseStatus(HttpStatus.OK)
    public Money getBalance(@RequestParam(name = "holder_id") Long accountHolderId, @RequestParam(name = "account_id") Long accountId){

        Optional<Account> account = accountRepository.findById(accountId);
        if(account.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "No account with this ID");
        }

        AccountHolder accountHolderFromDB = accountHolderRepository.findById(accountHolderId).orElseThrow(()
                -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Your ID is incorrect"));

        System.out.println("LIST: " + accountHolderFromDB.getAccountList());
        if (accountHolderFromDB.getAccountList().contains(account)){
            return account.get().getBalance();
        }else{
        return null;}
    }*/

    @GetMapping("/balance")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Money getBalance(@RequestParam(name = "holder_id") Long accountHolderId, @RequestParam(name = "account_id") Long accountId) {
        return accountHolderServiceInterface.getBalance(accountHolderId, accountId);
    }


    //FUnciona
    /*@PatchMapping("/transactions")
    @ResponseStatus(HttpStatus.OK)
    public void makeTransaction(@RequestParam(name = "account_id") Long accountHolderId,
                               @RequestParam(name = "target_id") Long targetId,
                               @RequestBody @Valid TransactionDTO transactionDTO) {
        accountHolderServiceInterface.transaction(accountHolderId, targetId, transactionDTO);
    }*/



    @PatchMapping("/transactions")
    @ResponseStatus(HttpStatus.OK)
    public void makeTransaction(@RequestParam(name = "target_id") Long targetId,
                                @RequestBody @Valid TransactionDTO transactionDTO) {
        accountHolderServiceInterface.transaction(targetId, transactionDTO);
    }
}

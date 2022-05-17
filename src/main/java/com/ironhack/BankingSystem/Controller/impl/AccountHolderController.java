package com.ironhack.BankingSystem.Controller.impl;

import com.ironhack.BankingSystem.Model.Users.AccountHolder;
import com.ironhack.BankingSystem.Service.interfaces.AccountHolderServiceInterface;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accountholders")
public class AccountHolderController {
    @Autowired
    private AccountHolderServiceInterface accountHolderServiceInterface;

    //ACa van los endpoint
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AccountHolder getAccountHolder(@PathVariable Long id){
        return accountHolderServiceInterface.getAccountHolderById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void storeAccountHolder(@RequestBody @Valid AccountHolder accountHolder){
        accountHolderServiceInterface.saveAccountHolder(accountHolder);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateAccountHolder(@PathVariable Long id, @RequestBody @Valid AccountHolder accountHolder){
        accountHolderServiceInterface.updateAccountHolder(id, accountHolder);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAccountHolder(@PathVariable Long id){
        accountHolderServiceInterface.deleteAccountHolder(id);
    }
}


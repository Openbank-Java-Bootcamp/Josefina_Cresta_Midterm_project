package com.ironhack.BankingSystem.Controller.impl;

import com.ironhack.BankingSystem.Service.interfaces.AccountHolderServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AccountHolderController {
    @Autowired
    private AccountHolderServiceInterface accountHolderService;

    //ACa van los endpoint
}

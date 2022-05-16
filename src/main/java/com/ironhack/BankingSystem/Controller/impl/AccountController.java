package com.ironhack.BankingSystem.Controller.impl;

import com.ironhack.BankingSystem.Service.interfaces.AccountServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AccountController {
    @Autowired
    private AccountServiceInterface accountService;

    //ACa van los endpoint
}

package com.ironhack.BankingSystem.Controller.impl;

import com.ironhack.BankingSystem.Service.interfaces.ThirdPartyServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ThirdPartyController {

    @Autowired
    private ThirdPartyServiceInterface thirdPartyService;

    //ACa van los endpoint
}
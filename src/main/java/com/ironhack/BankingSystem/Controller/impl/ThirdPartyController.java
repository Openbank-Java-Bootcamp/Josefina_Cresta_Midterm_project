package com.ironhack.BankingSystem.Controller.impl;

import com.ironhack.BankingSystem.Controller.interfaces.ThirdPartyControllerInterface;
import com.ironhack.BankingSystem.Service.interfaces.ThirdPartyServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bank")
public class ThirdPartyController implements ThirdPartyControllerInterface {

    @Autowired
    private ThirdPartyServiceInterface thirdPartyService;

}

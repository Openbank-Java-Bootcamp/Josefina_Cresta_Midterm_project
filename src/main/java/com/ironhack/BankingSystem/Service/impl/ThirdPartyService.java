package com.ironhack.BankingSystem.Service.impl;

import com.ironhack.BankingSystem.Repository.Users.ThirdParyRepository;
import com.ironhack.BankingSystem.Service.interfaces.ThirdPartyServiceInterface;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ThirdPartyService implements ThirdPartyServiceInterface {

    @Autowired
    ThirdParyRepository thirdParyRepository;

}

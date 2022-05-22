package com.ironhack.BankingSystem.Controller.impl;


import com.ironhack.BankingSystem.Controller.interfaces.UserControllerInterface;
import com.ironhack.BankingSystem.Model.secutiry.User;
import com.ironhack.BankingSystem.Repository.Accounts.AccountRepository;
import com.ironhack.BankingSystem.Service.interfaces.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bank")
public class UserController implements UserControllerInterface {

    @Autowired
    private UserServiceInterface userService;

    @Autowired
    private AccountRepository accountRepository;

    @GetMapping()
    @ResponseStatus(HttpStatus.I_AM_A_TEAPOT) // XD
    public void nothing() {
    }

    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveUser(@RequestBody User user) {
        userService.saveUser(user);
    }

    
    @GetMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    public List<User> getUsers(){
        return userService.getUsers();
    }





}

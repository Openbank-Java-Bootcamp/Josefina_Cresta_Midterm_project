package com.ironhack.BankingSystem.Controller.interfaces;


import com.ironhack.BankingSystem.Model.secutiry.User;

import java.util.List;

public interface UserControllerInterface {
    void saveUser(User user);
    List<User> getUsers();
}

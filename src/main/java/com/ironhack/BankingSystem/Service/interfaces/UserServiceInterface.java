package com.ironhack.BankingSystem.Service.interfaces;


import com.ironhack.BankingSystem.Model.secutiry.Role;
import com.ironhack.BankingSystem.Model.secutiry.User;

import java.util.List;

public interface UserServiceInterface {
    User saveUser(User user);
   /* User saveAdmin(Admin admin);*/
    Role saveRole(Role role);
    void addRoleToUser(String username, String roleName);
     User getUser(String username);
    List<User> getUsers();
}

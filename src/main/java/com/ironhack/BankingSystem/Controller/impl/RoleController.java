package com.ironhack.BankingSystem.Controller.impl;

import com.ironhack.BankingSystem.Controller.interfaces.RoleControllerInterface;
import com.ironhack.BankingSystem.DTO.RoleToUserDTO;
import com.ironhack.BankingSystem.Model.secutiry.Role;
import com.ironhack.BankingSystem.Service.interfaces.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class RoleController implements RoleControllerInterface {

    @Autowired
    private UserServiceInterface userService;

 /*   @PostMapping("/roles")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveRole(@RequestBody Role role) {
        userService.saveRole(role);
    }

    @PostMapping("/roles/addtouser")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addRoleToUser(@RequestBody RoleToUserDTO roleToUserDTO) {
        userService.addRoleToUser(roleToUserDTO.getUsername(), roleToUserDTO.getRoleName());
    }*/


}

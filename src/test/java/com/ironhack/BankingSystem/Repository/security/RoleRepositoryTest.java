package com.ironhack.BankingSystem.Repository.security;

import com.ironhack.BankingSystem.Model.secutiry.Role;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RoleRepositoryTest {

    @Autowired
    private RoleRepository roleRepository;

    private Role role1;
    private Role role2;
    private Role role3;


    @BeforeEach
    void setUp() {
        role1 = new Role("ROLE_USER");
        role2 = new Role("ROLE_ADMIN");
        role3 = new Role("ROLE_ACCOUNT_HOLDER");
        roleRepository.save(role1);
        roleRepository.save(role2);
        roleRepository.save(role3);
    }

    @AfterEach
    void tearDown() {
        roleRepository.deleteAll();
    }

    @Test
    public void testFindAll(){
        List<Role> roleList = roleRepository.findAll();
        assertEquals(3,roleList.size());
    }

    @Test
    public void testJPA_name(){
        Role role1 = roleRepository.findByName("ROLE_USER");
        Role role2 = roleRepository.findByName("ROLE_ADMIN");
        Role role3 = roleRepository.findByName("ROLE_ACCOUNT_HOLDER");
        assertEquals("ROLE_USER",role1.getName());
        assertEquals("ROLE_ADMIN",role2.getName());
        assertEquals("ROLE_ACCOUNT_HOLDER",role3.getName());
    }

}
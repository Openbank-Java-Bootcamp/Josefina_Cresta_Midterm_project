package com.ironhack.BankingSystem.Repository.security;

import com.ironhack.BankingSystem.Model.Users.AccountHolder;
import com.ironhack.BankingSystem.Model.secutiry.Role;
import com.ironhack.BankingSystem.Model.secutiry.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;
    private User user1;
    private User user2;
    private AccountHolder accountHolder;

    @BeforeEach
    void setUp() {
        user1 = new User(null, "John Doe", "john", "1234", new ArrayList<>());
        user2 = new  User( "James Smith", "james", "1234");
        accountHolder = new AccountHolder( "Maria", "paz", "$2b1234p", new ArrayList<>());
        userRepository.save(user1);
        userRepository.save(user2);
    }

    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
    }

    @Test
    void findByUsername() {
    }


    @Test
    void findByUserame_nameExactlyMatches_OK() {
        assertEquals(user1, userRepository.findByUsername("John Doe"));
        assertEquals(user2, userRepository.findByUsername("James Smith"));
        assertEquals(user1, userRepository.findByUsername("Maria"));
    }

    @Test
    void findByUserame_throwsException() {
        assertThrows(UnsupportedOperationException.class, () -> userRepository.findByUsername("John Doe"));

    }

    @Test
    void findByUsername_emptyList_throwsException() {
        userRepository.deleteAll();
        assertThrows(UnsupportedOperationException.class, () ->  userRepository.findByUsername("James Smith"));
    }

}
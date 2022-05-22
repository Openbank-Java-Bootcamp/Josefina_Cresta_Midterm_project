package com.ironhack.BankingSystem.Controller.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.BankingSystem.Model.Accounts.Account;
import com.ironhack.BankingSystem.Model.Accounts.CheckingAccounts;
import com.ironhack.BankingSystem.Model.Accounts.CreditCard;
import com.ironhack.BankingSystem.Model.Accounts.Savings;
import com.ironhack.BankingSystem.Model.Users.AccountHolder;
import com.ironhack.BankingSystem.Model.Utils.Money;
import com.ironhack.BankingSystem.Repository.Accounts.AccountRepository;
import com.ironhack.BankingSystem.Service.impl.AccountService;
import com.ironhack.BankingSystem.Service.impl.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("test")
class AccountControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private AccountService accountService;
    @Autowired
    private UserService userService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    BigDecimal b1 = new BigDecimal("4000255.23");
    BigDecimal b2 = new BigDecimal("25.23");
    BigDecimal c2 = new BigDecimal("0.1");
    BigDecimal c1 = new BigDecimal("150");
    Money balance1 = new Money(b1);
    Money balance2 = new Money(b2);
    Money limit = new Money(c1);
    AccountHolder accountHolder1 = new AccountHolder("Ana Luz");
    AccountHolder accountHolder2 = new AccountHolder("Alejandra cres");


    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        userService.saveUser(new AccountHolder("Josefina", "jofi", "1234j", new ArrayList<>()));
        userService.saveUser(new AccountHolder( "Maria", "paz", "$2b1234p", new ArrayList<>()));
        accountService.saveNewAccount(new Account(balance1, "A124", accountHolder1, "ANa"));
        accountService.saveNewAccount(new CheckingAccounts(balance2, "B456",
                accountHolder2, "Ale"));
        accountService.saveNewSavingsAccount(new Savings(balance1, "A124",
                accountHolder1, "ANa"));
        accountService.saveNewCreditAccount(new CreditCard(balance2, "B456",
                accountHolder2, "Ale", limit, c2));
    }

    @AfterEach
    void tearDown() {
        accountRepository.deleteAll();
    }


    @Test
    void createAccount() throws Exception {
        Account account = new Account(balance1, "A124", accountHolder1, "ANa");
        String body = objectMapper.writeValueAsString(account);
        MvcResult mvcResult = mockMvc.perform(post("bank/accounts")
                .content(body)
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isCreated()).andReturn();
    }

    @Test
    void createSavingsAccount() throws Exception {
        Savings savingAccount = new Savings(balance1, "A124", accountHolder1, "ANa");
        String body = objectMapper.writeValueAsString(savingAccount);
        MvcResult mvcResult = mockMvc.perform(post("bank/accounts/newSavings")
                .content(body)
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isCreated()).andReturn();
    }

    @Test
    void createCreditAccount() throws Exception {
        CreditCard creditCardAccount = new CreditCard(balance2, "B456", accountHolder2,
                "Ale", limit, c2);
        String body = objectMapper.writeValueAsString(creditCardAccount);
        MvcResult mvcResult = mockMvc.perform(post("bank/accounts/newCredit")
                .content(body)
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isCreated()).andReturn();
    }

    @Test
    void createCheckingAccount() throws Exception {
        CheckingAccounts checkingAccounts =new CheckingAccounts(balance2, "B456",
                accountHolder2, "Ale");
        String body = objectMapper.writeValueAsString(checkingAccounts);
        MvcResult mvcResult = mockMvc.perform(post("bank/accounts/newChecking")
                .content(body)
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isCreated()).andReturn();
    }


    @Test
    void updateProduct_ProductWithNonExistingId_NotFound() throws Exception {
        CheckingAccounts checkingAccounts =new CheckingAccounts(balance2, "B456",
                accountHolder2, "Ale");
        String body = objectMapper.writeValueAsString(checkingAccounts);
        MvcResult mvcResult = mockMvc.perform(post("bank/accounts/balance/{id}", 30)
                .content(body)
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isNotFound()).andReturn();
    }

}
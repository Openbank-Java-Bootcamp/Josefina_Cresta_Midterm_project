package com.ironhack.BankingSystem;

import com.ironhack.BankingSystem.Model.Accounts.Account;
import com.ironhack.BankingSystem.Model.Users.AccountHolder;
import com.ironhack.BankingSystem.Model.Utils.Money;
import com.ironhack.BankingSystem.Model.secutiry.Role;
import com.ironhack.BankingSystem.Model.secutiry.User;
import com.ironhack.BankingSystem.Service.impl.AccountHolderService;
import com.ironhack.BankingSystem.Service.impl.AccountService;
import com.ironhack.BankingSystem.Service.impl.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;
import java.util.ArrayList;

@SpringBootApplication
public class BankingSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankingSystemApplication.class, args);
	}

	@Bean
	PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}

	@Bean
	CommandLineRunner run(UserService userService, AccountHolderService accountHolderService, AccountService accountService){
		return args -> {
			userService.saveRole(new Role(null, "ROLE_CLIENT"));
			userService.saveRole(new Role(null, "ROLE_ADMIN"));
			userService.saveRole(new Role(null, "ROLE_THIRD"));

			userService.saveUser(new User(null, "John Doe", "john", "1234", new ArrayList<>()));
			userService.saveUser(new User(null, "James Smith", "james", "1234", new ArrayList<>()));
			userService.saveUser(new User(null, "Jane Carry", "jane", "1234", new ArrayList<>()));
			userService.saveUser(new User(null, "Chris Anderson", "chris", "1234", new ArrayList<>()));

			userService.addRoleToUser("john", "ROLE_CLIENT");
			userService.addRoleToUser("james", "ROLE_ADMIN");
			userService.addRoleToUser("josefina", "ROLE_CLIENT");
			userService.addRoleToUser("carlos", "ROLE_ADMIN");

			accountHolderService.saveAccountHolder(new AccountHolder("Aiko Tanaka"));
			accountHolderService.saveAccountHolder(new AccountHolder("Josefina Cresta"));

			BigDecimal b1 = new BigDecimal("4000255.23");
			Money balance1= new Money(b1);
			AccountHolder accountHolder1 = new AccountHolder("Aiko Tanaka");


			accountService.saveNewAccount(new Account(balance1, "4567ES", accountHolder1, "Raymond"));


			/*blogPostService.saveBlogPost(new BlogPost(1L, "Boost Your Productivity with 10 Easy Tips", "Productivity - we all want it but it seems ..."));
			blogPostService.saveBlogPost(new BlogPost(2L, "How to Focus", "Do you ever sit down to work and find yourself ..."));
			blogPostService.saveBlogPost(new BlogPost(3L, "Learn to Speed Read in 30 Days", "Knowledge, not ability, is the great determiner of ..."));*/
		};
	}

}

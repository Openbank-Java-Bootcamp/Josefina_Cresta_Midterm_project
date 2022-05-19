package com.ironhack.BankingSystem;

import com.ironhack.BankingSystem.Model.Accounts.Account;
import com.ironhack.BankingSystem.Model.Accounts.Savings;
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
			userService.saveRole(new Role(null, "ROLE_USER"));
			userService.saveRole(new Role(null, "ROLE_ADMIN"));

			userService.saveUser(new User(null, "John Doe", "john", "1234", new ArrayList<>()));
			userService.saveUser(new User(null, "James Smith", "james", "1234", new ArrayList<>()));
			userService.saveUser(new User(null, "Jane Carry", "jane", "1234", new ArrayList<>()));
			userService.saveUser(new User(null, "Chris Anderson", "chris", "1234", new ArrayList<>()));

			userService.addRoleToUser("john", "ROLE_USER");
			userService.addRoleToUser("james", "ROLE_ADMIN");
			userService.addRoleToUser("jane", "ROLE_USER");
			userService.addRoleToUser("chris", "ROLE_ADMIN");
			userService.addRoleToUser("chris", "ROLE_USER");

			accountHolderService.saveAccountHolder(new AccountHolder("Aiko Tanaka"));
			accountHolderService.saveAccountHolder(new AccountHolder("Josefina Cresta"));

			/*BigDecimal b1 = new BigDecimal("4000255.23");
			Money balance1= new Money(b1);
			AccountHolder accountHolder1 = new AccountHolder("Aiko Tanaka");


			accountService.saveNewAccount(new Account(balance1, "4567ES",
					accountHolder1, "Raymond"));
			accountService.saveNewSavingsAccount(new Savings(balance1,"4567ES",
					accountHolder1, "Raymond"));
*/

		};
	}

}
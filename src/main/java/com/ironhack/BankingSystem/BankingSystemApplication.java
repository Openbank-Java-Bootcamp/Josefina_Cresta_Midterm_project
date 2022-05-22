package com.ironhack.BankingSystem;

import com.ironhack.BankingSystem.Model.Accounts.Account;
import com.ironhack.BankingSystem.Model.Accounts.CheckingAccounts;
import com.ironhack.BankingSystem.Model.Accounts.CreditCard;
import com.ironhack.BankingSystem.Model.Accounts.Savings;
import com.ironhack.BankingSystem.Model.Users.AccountHolder;
import com.ironhack.BankingSystem.Model.Utils.Money;
import com.ironhack.BankingSystem.Model.secutiry.Role;
import com.ironhack.BankingSystem.Model.secutiry.User;
import com.ironhack.BankingSystem.Service.impl.AccountHolderService;
import com.ironhack.BankingSystem.Service.impl.AccountService;
import com.ironhack.BankingSystem.Service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;

@SpringBootApplication
public class BankingSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankingSystemApplication.class, args);
	}
	@Autowired
	public Environment environment;
	@Bean
	PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}


	@Bean
	CommandLineRunner run(UserService userService, AccountHolderService accountHolderService, AccountService accountService) {

		BigDecimal b1 = new BigDecimal("4000255.23");
		BigDecimal b2 = new BigDecimal("25.23");
		BigDecimal c2 = new BigDecimal("0.1");
		BigDecimal c1 = new BigDecimal("150");
		Money balance1 = new Money(b1);
		Money balance2 = new Money(b2);
		Money limit = new Money(c1);
		AccountHolder accountHolder1 = new AccountHolder("Ana Luz");
		AccountHolder accountHolder2 = new AccountHolder("Alejandra cres");

		return args -> {
			if (!Arrays.asList(environment.getActiveProfiles()).contains("test")) {
				userService.saveRole(new Role(null, "ROLE_USER"));
				userService.saveRole(new Role(null, "ROLE_ADMIN"));
				userService.saveRole(new Role(null, "ROLE_ACCOUNT_HOLDER"));


				userService.saveUser(new User(null, "John Doe", "john",
						"1234", new ArrayList<>()));
				userService.saveUser(new User(null, "James Smith", "james",
						"1234", new ArrayList<>()));
				userService.saveUser(new User(null, "Jane Carry", "jane",
						"1234", new ArrayList<>()));
				userService.saveUser(new User(null, "Chris Anderson", "chris",
						"1234", new ArrayList<>()));

				userService.addRoleToUser("john", "ROLE_ACCOUNT_HOLDER");
				userService.addRoleToUser("james", "ROLE_ADMIN");
				userService.addRoleToUser("jane", "ROLE_USER");
				userService.addRoleToUser("chris", "ROLE_ADMIN");
				userService.addRoleToUser("chris", "ROLE_USER");


				userService.saveUser(new AccountHolder("Josefina", "jofi",
						"1234j", new ArrayList<>()));
				userService.saveUser(new AccountHolder( "Maria", "paz",
						"$2b1234p", new ArrayList<>()));

				userService.addRoleToUser("jofi", "ROLE_ACCOUNT_HOLDER");
				userService.addRoleToUser("paz", "ROLE_ACCOUNT_HOLDER");

				accountService.saveNewAccount(new Account(balance1, "A124",
						accountHolder1, "ANa"));
				accountService.saveNewAccount(new CheckingAccounts(balance2, "B456",
						accountHolder2, "Ale"));
				accountService.saveNewSavingsAccount(new Savings(balance1, "A124",
						accountHolder1, "ANa"));
				accountService.saveNewCreditAccount(new CreditCard(balance2, "B456",
						accountHolder2, "Ale", limit, c2));
				accountService.saveNewCheckingAccount(new CheckingAccounts(balance2, "B456",
						accountHolder2, "Ale"));

			}

		};
	}

}
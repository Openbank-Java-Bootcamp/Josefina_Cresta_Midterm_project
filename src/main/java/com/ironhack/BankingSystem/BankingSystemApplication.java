package com.ironhack.BankingSystem;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class BankingSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankingSystemApplication.class, args);
	}

	@Bean
	PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}

/*	@Bean
	CommandLineRunner run(UserService userService, AuthorService authorService, BlogPostService blogPostService){
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

			authorService.saveAuthor(new Author("Aiko Tanaka"));
			authorService.saveAuthor(new Author("Jonas Schmidt"));
			authorService.saveAuthor(new Author("Cas Van Dijk"));

			blogPostService.saveBlogPost(new BlogPost(1L, "Boost Your Productivity with 10 Easy Tips", "Productivity - we all want it but it seems ..."));
			blogPostService.saveBlogPost(new BlogPost(2L, "How to Focus", "Do you ever sit down to work and find yourself ..."));
			blogPostService.saveBlogPost(new BlogPost(3L, "Learn to Speed Read in 30 Days", "Knowledge, not ability, is the great determiner of ..."));
		};
	}*/

}

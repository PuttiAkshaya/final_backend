package com.klu;

import com.klu.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.klu.model.User;

@SpringBootApplication
public class ArtbackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(ArtbackendApplication.class, args);
	}

	@Bean
	public CommandLineRunner dumpUsers(UserRepository repository, PasswordEncoder encoder) {
		return args -> {
			System.out.println("======= REGISTERED USERS IN DB =======");
            
            // Force create a test user
            if (repository.findByUsername("test").isEmpty()) {
                User testUser = new User();
                testUser.setUsername("test");
                testUser.setEmail("test@test.com");
                testUser.setPassword(encoder.encode("123"));
                testUser.setRole(com.klu.model.Role.VISITOR);
                repository.save(testUser);
                System.out.println("CREATED TEST USER: test / 123");
            }

			repository.findAll().forEach(u -> System.out.println("Username: " + u.getUsername() + " | Email: " + u.getEmail()));
			System.out.println("=======================================");
		};
	}
}

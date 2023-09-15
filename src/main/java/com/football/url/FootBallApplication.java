package com.football.url;

import com.football.url.entity.Role;
import com.football.url.repository.RoleRepository;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "Cipher Blog",
				description = "Spring Boot app with Rest APIs documentation",
				version = "v1.0",
				contact = @Contact(
						name = "Babu Gyara",
						email = "gyarababu9@gmail.com"
				)
		)
)
public class FootBallApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(FootBallApplication.class, args);
	}

	@Autowired
	private RoleRepository roleRepository;
	@Override
	public void run(String... args) throws Exception {
		try {
			if (roleRepository.findByName("ROLE_ADMIN") == null) {
				Role adminRole = new Role();
				adminRole.setName("ROLE_ADMIN");
				roleRepository.save(adminRole);
			}

			if (roleRepository.findByName("ROLE_USER") == null) {
				Role userRole = new Role();
				userRole.setName("ROLE_USER");
				roleRepository.save(userRole);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	}
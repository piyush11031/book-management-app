package com.booknetworkapi;

import com.booknetworkapi.entity.user.Role;
import com.booknetworkapi.entity.user.User;
import com.booknetworkapi.repository.RoleRepository;
import com.booknetworkapi.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "AuditAwareBean")
@AllArgsConstructor
public class BookNetworkApiApplication implements CommandLineRunner {

	private final UserRepository userRepository;
	private final RoleRepository roleRepository;
	private final PasswordEncoder encoder;

	public static void main(String[] args) {
		SpringApplication.run(BookNetworkApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		if(roleRepository.findAll().isEmpty()){
			Role adminRole = new Role("ADMIN");
			Role userRole = new Role("USER");

			adminRole = roleRepository.save(adminRole);
			userRole = roleRepository.save(userRole);

			Set<Role> adminSet = new HashSet<>();
			adminSet.add(adminRole);

			Set<Role> userSet = new HashSet<>();
			userSet.add(userRole);

			User admin = User.builder()
					.email("admin")
					.password(encoder.encode("password"))
					.authorities(adminSet)
					.build();

			User testUser = User.builder()
					.email("test user")
					.password(encoder.encode("password"))
					.authorities(userSet)
					.build();
			userRepository.save(admin);
			userRepository.save(testUser);
		}
	}
}

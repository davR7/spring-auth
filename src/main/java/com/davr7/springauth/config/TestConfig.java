package com.davr7.springauth.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.davr7.springauth.domain.Role;
import com.davr7.springauth.domain.User;
import com.davr7.springauth.domain.UserRole;
import com.davr7.springauth.repositories.UserRepository;

import lombok.Builder;

@Builder
@Configuration
@Profile(value = "test")
public class TestConfig implements CommandLineRunner{
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	UserRepository userRepository;
	
	@Override
	public void run(String... args) throws Exception {
		Role r1 = Role.builder().name(UserRole.ROLE_ADMIN).build();
		
		Role r2 = Role.builder().name(UserRole.ROLE_BASIC).build();
		
		User u1 = User.builder()
				.fullname("Davi Melo")
				.email("davi@test.com")
				.username("davi78")
				.password(passwordEncoder.encode("12345"))
				.roles(Arrays.asList(r1, r2))
				.build();
		
		User u2 = User.builder()
				.fullname("Ana Mendes")
				.email("ana@test.com")
				.username("ana23")
				.password(passwordEncoder.encode("12345"))
				.roles(Arrays.asList(r2))
				.build();
		
		userRepository.saveAll(Arrays.asList(u1, u2));
	}
}

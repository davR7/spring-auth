package com.davr7.springauth.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.davr7.springauth.domain.Role;
import com.davr7.springauth.domain.User;
import com.davr7.springauth.domain.UserDetailsImpl;
import com.davr7.springauth.domain.UserRole;
import com.davr7.springauth.dtos.CreateUserDTO;
import com.davr7.springauth.dtos.LoginUserDTO;
import com.davr7.springauth.infra.security.JwtUtil;
import com.davr7.springauth.repositories.RoleRepository;
import com.davr7.springauth.repositories.UserRepository;

@Service
public class UserService {
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RoleRepository roleRepository;

	public List<User> readAllUsers() {
		return userRepository.findAll();
	}

	public String authenticaticateUser(LoginUserDTO obj) {
		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = 
				new UsernamePasswordAuthenticationToken(obj.username(), obj.password());

		Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		
		return jwtUtil.generateToken(userDetails);
	}
	
	public User createUser(CreateUserDTO obj) {
		User newUser = User.builder()
				.fullname(obj.fullname())
				.email(obj.email())
				.username(obj.username())
				.password(passwordEncoder.encode(obj.password()))
				.roles(createRoles(obj.roles())).build();

		return userRepository.save(newUser);
	}
	
	public List<Role> createRoles(List<UserRole> roles) {
		return roles.stream()
				.map(role -> roleRepository
						.findByName(role)
						.orElse(new Role(null, role)
				))
                .collect(Collectors.toList());
	}
}

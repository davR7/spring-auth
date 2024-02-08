package com.davr7.springauth.resources;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.davr7.springauth.domain.User;
import com.davr7.springauth.dtos.UserRegisterDTO;
import com.davr7.springauth.services.UserService;

@RestController
@RequestMapping("/auth")
public class AuthenticationResource {
	
	@Autowired
	UserService service;
	
	@PostMapping("/signup")
	public ResponseEntity<User> signup(UserRegisterDTO obj) {

		User user = service.createUser(obj);

		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(user.getId())
				.toUri();
		
		return ResponseEntity.created(location).body(user);
	}
}

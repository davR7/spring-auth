package com.davr7.springauth.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.davr7.springauth.domain.User;
import com.davr7.springauth.services.UserService;

@RestController
@RequestMapping("users")
public class UserResource {
	
	@Autowired
	UserService service;
	
	@GetMapping
	public ResponseEntity<List<User>> readAllUsersHandler() {
		List<User> users = service.readAllUsers();
		return ResponseEntity.ok().body(users);
	}
}

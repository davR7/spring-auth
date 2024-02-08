package com.davr7.springauth.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.davr7.springauth.services.UserService;

@RestController
@RequestMapping("/auth")
public class AuthenticationResource {
	
	@Autowired
	UserService service;
}

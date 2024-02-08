package com.davr7.springauth.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.davr7.springauth.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository repository;
}

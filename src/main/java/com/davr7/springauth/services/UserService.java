package com.davr7.springauth.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.davr7.springauth.domain.User;
import com.davr7.springauth.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository repository;
	
	public List<User> readAllUsers(){
		return repository.findAll();
	}
}

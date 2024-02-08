package com.davr7.springauth.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.davr7.springauth.domain.User;
import com.davr7.springauth.domain.UserDetailsImpl;
import com.davr7.springauth.repositories.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	UserRepository repository;
	
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = repository.findByUsername(username).orElseThrow(() -> new RuntimeException("Resource not found: "+username));
		return new UserDetailsImpl(user);
	}
}

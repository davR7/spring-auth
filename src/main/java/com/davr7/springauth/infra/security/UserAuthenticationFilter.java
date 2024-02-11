package com.davr7.springauth.infra.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.davr7.springauth.domain.User;
import com.davr7.springauth.domain.UserDetailsImpl;
import com.davr7.springauth.repositories.UserRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class UserAuthenticationFilter extends OncePerRequestFilter {
	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private UserRepository repository;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) {
		String token = recoveryToken(request);

		if (token != null) {
			String subject = jwtUtil.verifyToken(token);
			
			User user = repository.findByUsername(subject).orElseThrow(() -> new RuntimeException("User does not exist"));
			
			UserDetailsImpl userDetails = new UserDetailsImpl(user);
			
			UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
					userDetails.getUsername(), null, userDetails.getAuthorities());

			SecurityContextHolder.getContext().setAuthentication(authentication);
		}

		try {
			filterChain.doFilter(request, response);
		} catch (IOException | ServletException e) {
			throw new RuntimeException("Error during filter. " + e);
		}
	}

	private String recoveryToken(HttpServletRequest request) {
		String AuthHeader = request.getHeader("Authorization");

		if (AuthHeader != null && AuthHeader.startsWith("Bearer")) {
			return AuthHeader.substring(7);
		}

		return null;
	}
}

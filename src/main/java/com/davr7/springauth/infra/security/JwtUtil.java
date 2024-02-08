package com.davr7.springauth.infra.security;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.davr7.springauth.domain.User;

@Component
public class JwtUtil {
    private static final String SECRET_KEY = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXUyJ9.eyJpc3MiOiJhdXRoMCJ9.AbIJTDMFc7yUa5MhvcP03nJPyCPzZtQcGEp-zWfOkZZ"; 
	
	private static final String ISSUER = "spring-auth"; 
	
	public String generateToken(User user) {
		try {
			Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
		    String token = JWT.create()
		        .withIssuer(ISSUER)
		        .withSubject(user.getId())
		        .withIssuedAt(creationDate())
		        .withExpiresAt(expirationDate())
		        .sign(algorithm);
		    return token;
		} catch (JWTCreationException exception){
		    throw new JWTCreationException("Invalid or expired token", exception);
		}
	}
	
	public String verifyToken(String token) {
		try {
			Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
		    return JWT.require(algorithm) 
		        .withIssuer(ISSUER)
		        .build()
		        .verify(token)
		        .getSubject();
		} catch (JWTVerificationException exception){
		    throw new JWTVerificationException("Invalid signature");
		}
	}
	
	private Instant creationDate() {
		return ZonedDateTime.now(ZoneId.of("America/Recife")).toInstant();
	}
	
	private Instant expirationDate() {
		return ZonedDateTime.now(ZoneId.of("America/Recife")).plusDays(1).toInstant();
	}
}

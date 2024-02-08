package com.davr7.springauth.domain.user;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public enum UserRole {
	ADMIN(1),
	BASIC(2);
	
	private int userCode;
	
	private UserRole(int userCode) {
		this.userCode = userCode;
	};

	public int getRoleCode() {
		return userCode;
	};
	
	public GrantedAuthority toGrantedAuthority() {
		return new SimpleGrantedAuthority("ROLE_"+ name());
	}
	
	public static UserRole valueOf(int userCode) {
		for (UserRole value : UserRole.values()) {
			if (value.getRoleCode() == userCode) {
				return value;
			}
		}
		throw new IllegalArgumentException("Invalid UserRole Code");
	}
}

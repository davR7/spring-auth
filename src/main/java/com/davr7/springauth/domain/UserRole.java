package com.davr7.springauth.domain;

public enum UserRole {
	ADMIN("ROLE_ADMIN"),
	BASIC("ROLE_BASIC");
	
	private String role;

    UserRole(String role){
        this.role = role;
    }

    public String getRole(){
        return role;
    }
}

package com.davr7.springauth.dtos;

import com.davr7.springauth.domain.UserRole;

public record CreateUserDTO(String fullname, String email, String username, String password, UserRole role) {
}
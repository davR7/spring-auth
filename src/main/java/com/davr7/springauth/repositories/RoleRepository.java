package com.davr7.springauth.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.davr7.springauth.domain.Role;
import com.davr7.springauth.domain.UserRole;

public interface RoleRepository extends JpaRepository<Role, String> {
	Optional<Role> findByName(UserRole name);
}

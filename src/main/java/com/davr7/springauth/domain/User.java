package com.davr7.springauth.domain;

import java.io.Serializable;
import java.util.List;

import com.davr7.springauth.dtos.UserRegisterDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of="id")
@Builder
public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;
	private String fullname;
	@Column(unique = true)
	private String email;
	@Column(unique = true)
	private String username;
	@JsonIgnore
	private String password;
	private Integer role;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(name="users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name="role_id"))
	private List<UserRole> roles;
	
	public User(UserRegisterDTO UserRegister) {
		this.fullname = UserRegister.fullname();
		this.email = UserRegister.email();
		this.username = UserRegister.username();
		this.password = UserRegister.password();
		this.setRole(UserRegister.role());
	}

	public User(String id, String fullname, String email, String username, String password, UserRole role) {
		this.id = id;
		this.fullname = fullname;
		this.email = email;
		this.username = username;
		this.password = password;
		this.setRole(role);
	}
	
	public UserRole getRole(){
		return UserRole.valueOf(role);
	}
	
	public void setRole(UserRole role) {
		if (role != null) {
			this.role = role.getRoleCode();
		}
	}
}

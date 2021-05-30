package com.example.pawel.expense.model;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id")
	private Long id;

	@Column(name = "user_name")
//    @Length(min = 6, message = "Your username must have at least 6 characters")
	// @NotEmpty(message = "Cannot be null")
	private String username;

	@Column(name = "email")
//    @Email(message = "*Please provide a valid Email")
//    @NotEmpty(message = "*Please provide an email")
	private String email;

	@Column(name = "password")
//    @Length(min = 5, message = "Your password must have at least 6 characters")
	private String password;

	@Column(name = "name")
//    @NotEmpty(message = "*Please provide your name")
	private String name;

//	@Column(name = "last_name")
////    @NotEmpty(message = "*Please provide your last name")
//	private String lastName;

//	@Column(name = "active")
//	private Boolean active;

	@ManyToMany(cascade = CascadeType.MERGE)
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles;

	// private Collection<? extends GrantedAuthority> authorities;

    public User() {

    }

    public User(String name, String username, String email, String password) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
    }

//	public Collection<? extends GrantedAuthority> getAuthorities() {
//		return authorities;
//	}
//
//	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
//		this.authorities = authorities;
//	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}


	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
}

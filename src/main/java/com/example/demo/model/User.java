/**
 * 
 */
package com.example.demo.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author virens
 *
 */
@Entity
@Table(name = "USERS")
public class User {

	public User() {
	}

	public User(String username) {
		this.username = username;
	}

	public User(User user) {
		this.id = user.getId();
		this.username = user.getUsername();
		this.password = user.getPassword();
		this.tokenExpired = user.isTokenExpired();
		this.enabled = user.isEnabled();
		this.roles = user.getRoles();
	}

	@Id
	@Column(name = "ID")
	@GeneratedValue
	private Long id;

	@Column(name = "username", unique = true, nullable = false, length = 255)
	private String username;

	@NotBlank
	@Size(max = 255)
	@Column(name = "password", nullable = false, length = 255)
	private String password;

	@NotNull
	@Column(name = "enabled", nullable = false)
	private boolean enabled;

	@NotNull
	@Column(name = "tokenExpired", nullable = false)
	private boolean tokenExpired;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "name")
	@JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
	private Set<Role> roles;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isTokenExpired() {
		return tokenExpired;
	}

	public void setTokenExpired(boolean tokenExpired) {
		this.tokenExpired = tokenExpired;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

}

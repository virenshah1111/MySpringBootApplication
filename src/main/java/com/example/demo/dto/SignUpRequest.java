/**
 * 
 */
package com.example.demo.dto;

import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.example.demo.model.Role;

/**
 * @author virens
 *
 */
public class SignUpRequest {

	@NotBlank
	@Size(min = 3, max = 25)
	private String username;

	@NotBlank
	@Size(min = 4, max = 40)
	private String password;

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

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

}

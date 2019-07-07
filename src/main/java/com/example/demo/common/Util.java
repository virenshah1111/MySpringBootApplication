/**
 * 
 */
package com.example.demo.common;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo.model.User;

/**
 * @author virens
 *
 */
public class Util {

	public static User getPrincipal() {
		User user = null;
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Object principal = auth.getPrincipal();
		if (principal instanceof UserDetails) {
			UserDetails userDetails = ((UserDetails) principal);
			user = new User(userDetails.getUsername(), userDetails.isEnabled(), !userDetails.isCredentialsNonExpired());
		} else {
			user = new User(principal.toString());
		}
		return user;
	}

}

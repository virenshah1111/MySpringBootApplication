/**
 * @author virens
 *
*/
package com.example.demo;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo.model.User;

public class Util {

	public static User getPrincipal() {
		return new User(getCurrentUsername());
	}

	public static String getCurrentUsername() {
		String userName = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			userName = ((UserDetails) principal).getUsername();
		} else {
			userName = principal.toString();
		}
		return userName;
	}

}

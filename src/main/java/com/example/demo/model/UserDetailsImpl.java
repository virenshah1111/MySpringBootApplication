/**
 * 
 */
package com.example.demo.model;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author virens
 *
 */
public class UserDetailsImpl extends User implements UserDetails {

	
	private static final long serialVersionUID = 1L;

	public UserDetailsImpl(User user) {
        super(user);
    }
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		 return getRoles()
	                .stream()
	                .map(role-> new SimpleGrantedAuthority("ROLE_"+role.getRolename()))
	                .collect(Collectors.toList());
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return this.isEnabled();
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

}

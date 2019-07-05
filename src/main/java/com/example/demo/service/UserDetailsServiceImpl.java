/**
 * 
 */
package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.model.User;
import com.example.demo.model.UserDetailsImpl;
import com.example.demo.repository.UserRepository;

/**
 * @author virens
 *
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) {
		Optional<User> optionalUser = userRepository.findByUsername(username);
		return Optional.ofNullable(optionalUser).orElseThrow(() -> new UsernameNotFoundException("Username Not Found"))
				.map(UserDetailsImpl::new).orElse(new UserDetailsImpl(new User()));
	}

}

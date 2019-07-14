/**
 * 
 */
package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

/**
 * @author virens
 *
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Override
	public void save(User user) {
		userRepository.save(user);
	}

	@Override
	public void saveAll(List<User> userList) {
		userRepository.saveAll(userList);
	}

	@Override
	public boolean existsByUsername(String username) {
		return userRepository.findByUsername(username).isPresent();
	}

	@Override
	public List<User> findAll() {
		return (List<User>) userRepository.findAll();
	}

}

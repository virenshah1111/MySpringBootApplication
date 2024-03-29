/**
 * 
 */
package com.example.demo.service;

import java.util.List;

import com.example.demo.model.User;

/**
 * @author virens
 *
 */
public interface UserService {

	void save(User user);

	void saveAll(List<User> userList);

	List<User> findAll();

	boolean existsByUsername(String username);

}

/**
 * 
 */
package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Role;

/**
 * @author virens
 *
 */
public interface RoleService {

	void saveAll(List<Role> roleList);

	List<Role> findAll();

}

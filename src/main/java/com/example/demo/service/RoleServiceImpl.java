/**
 * 
 */
package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Role;
import com.example.demo.repository.RoleRepository;

/**
 * @author virens
 *
 */
@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	RoleRepository roleRepository;

	@Override
	public void saveAll(List<Role> roleList) {
		roleRepository.saveAll(roleList);
	}

	@Override
	public List<Role> findAll() {
		return (List<Role>) roleRepository.findAll();
	}

}

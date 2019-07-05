/**
 * 
 */
package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Role;

/**
 * @author virens
 *
 */
@Repository
public interface RoleRepository extends CrudRepository<Role, Integer> {

}

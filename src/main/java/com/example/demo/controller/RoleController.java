/**
 * 
 */
package com.example.demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.common.ResponseUtil;
import com.example.demo.model.Role;
import com.example.demo.service.RoleService;

/**
 * @author virens
 *
 */
@RestController
@RequestMapping("/roles")
public class RoleController {

	@Autowired
	RoleService roleService;

	@PostMapping
	public ResponseEntity<ResponseUtil<Role>> saveAll(@Valid @RequestBody List<Role> roleList) {
		roleService.saveAll(roleList);
		return new ResponseEntity<>(new ResponseUtil<Role>("Data Saved Successfully."), HttpStatus.CREATED);
	}

	@GetMapping()
	public ResponseEntity<ResponseUtil<List<Role>>> getAll() {
		ResponseUtil<List<Role>> responseUtil = new ResponseUtil<>();
		responseUtil.setData(roleService.findAll());
		return new ResponseEntity<>(responseUtil, HttpStatus.OK);
	}

}

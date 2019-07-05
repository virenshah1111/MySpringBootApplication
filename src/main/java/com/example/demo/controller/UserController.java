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
import com.example.demo.model.User;
import com.example.demo.service.UserService;

/**
 * @author virens
 *
 */
@RestController
@RequestMapping(path = "/users")
public class UserController {

	@Autowired
	UserService userService;

	@PostMapping
	public ResponseEntity<ResponseUtil<User>> saveAll(@Valid @RequestBody List<User> userList) {
		userService.saveAll(userList);
		return new ResponseEntity<>(new ResponseUtil<User>("Data Saved Successfully."), HttpStatus.CREATED);
	}

	@GetMapping()
	public ResponseEntity<ResponseUtil<List<User>>> getAll() {
		ResponseUtil<List<User>> responseUtil = new ResponseUtil<>();
		responseUtil.setData(userService.findAll());
		return new ResponseEntity<>(responseUtil, HttpStatus.OK);
	}

}

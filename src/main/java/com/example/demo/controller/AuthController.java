/**
 * 
 */
package com.example.demo.controller;

import java.util.Collections;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.common.ResponseUtil;
import com.example.demo.common.ValidationErrorMessage;
import com.example.demo.config.JwtTokenProvider;
import com.example.demo.dto.JwtAuthenticationResponse;
import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.SignUpRequest;
import com.example.demo.exception.ValidationException;
import com.example.demo.model.Role;
import com.example.demo.model.RoleName;
import com.example.demo.model.User;
import com.example.demo.service.UserService;

/**
 * @author virens
 *
 */

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	JwtTokenProvider tokenProvider;

	@Autowired
	UserService userService;

	@PostMapping("/login")
	public ResponseEntity<ResponseUtil<JwtAuthenticationResponse>> authenticateUser(
			@Valid @RequestBody LoginRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = tokenProvider.generateToken(authentication);

		ResponseUtil<JwtAuthenticationResponse> responseUtil = new ResponseUtil<>();
		responseUtil.setData(new JwtAuthenticationResponse(jwt));
		responseUtil.setMessage("Login Successfully.");

		return ResponseEntity.ok(responseUtil);
	}

	@PostMapping("/signup")
	public ResponseEntity<ResponseUtil<?>> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {

		if (userService.existsByUsername(signUpRequest.getUsername())) {
			ValidationErrorMessage validationErrorMessage = new ValidationErrorMessage();
			validationErrorMessage.setErrorCode("Duplicate");
			validationErrorMessage.setErrorMessage("Username is already taken!");
			validationErrorMessage.setField("username");
			validationErrorMessage.setRejectedValue(signUpRequest.getUsername());
			throw new ValidationException(validationErrorMessage);
		}

		User user = new User();
		user.setUsername(signUpRequest.getUsername());
		user.setPassword(signUpRequest.getPassword());
		user.setRoles(Collections.singleton(new Role(RoleName.USER)));

		userService.save(user);

		return ResponseEntity.ok(new ResponseUtil<>("User registered successfully"));
	}

}

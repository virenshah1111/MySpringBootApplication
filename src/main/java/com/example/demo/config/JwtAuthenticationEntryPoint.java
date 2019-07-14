/**
 * 
 */
package com.example.demo.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

/**
 * @author virens
 *
 */
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			AuthenticationException authenticationException) throws IOException, ServletException {
		System.out.println("Responding with unauthorized error. Message - {}" + authenticationException.getMessage());
		httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, authenticationException.getMessage());
	}

}

/**
 * 
 */
package com.example.demo.config;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import com.example.demo.model.UserDetailsImpl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

/**
 * @author virens
 *
 */
@Component
public class JwtTokenProvider {

	private static final String AUTHORITIES_KEY = "auth";

	@Value("${app.jwtSecret}")
	private String jwtSecret;

	@Value("${app.jwtExpirationInMs}")
	private int jwtExpirationInMs;

	public String generateToken(Authentication authentication) {

		UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();
		Date now = new Date();
		Date expiryDate = new Date(now.getTime() + jwtExpirationInMs);
		String authorities = authentication.getAuthorities().stream().map(authority -> authority.getAuthority())
				.collect(Collectors.joining(","));

		return Jwts.builder().setSubject(userPrincipal.getUsername()).claim(AUTHORITIES_KEY, authorities)
				.setIssuedAt(new Date()).setExpiration(expiryDate).signWith(SignatureAlgorithm.HS512, jwtSecret)
				.compact();

	}

	/*public String getUserNameFromJWT(String token) {
		Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
		return claims.getSubject();
	}*/

	public Authentication getAuthentication(String token) {

		Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();

		Collection<? extends GrantedAuthority> authorities = Arrays
				.asList(claims.get(AUTHORITIES_KEY).toString().split(",")).stream()
				.map(authority -> new SimpleGrantedAuthority(authority)).collect(Collectors.toList());

		User principal = new User(claims.getSubject(), "", authorities);

		return new UsernamePasswordAuthenticationToken(principal, "", authorities);
	}

	public boolean validateToken(String authToken) {
		try {
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
			return true;
		} catch (SignatureException ex) {
			System.out.println("Invalid JWT signature");
		} catch (MalformedJwtException ex) {
			System.out.println("Invalid JWT token");
		} catch (ExpiredJwtException ex) {
			System.out.println("Expired JWT token");
		} catch (UnsupportedJwtException ex) {
			System.out.println("Unsupported JWT token");
		} catch (IllegalArgumentException ex) {
			System.out.println("JWT claims string is empty.");
		}
		return false;
	}

}

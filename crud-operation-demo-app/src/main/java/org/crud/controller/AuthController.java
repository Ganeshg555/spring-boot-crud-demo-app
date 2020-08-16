package org.crud.controller;

import org.crud.model.AuthRequest;
import org.crud.model.AuthResponse;
import org.crud.service.MyUserDetailsService;
import org.crud.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private MyUserDetailsService myUserDetailsService;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@PostMapping("/authenticate")
	public ResponseEntity<?> authenticate(@RequestBody AuthRequest authRequest) {
		try {
			this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword()));
		} catch(DisabledException de) {
			throw new DisabledException("User is disabled");
		} catch(BadCredentialsException bd) {
			throw new BadCredentialsException("Invalid user credentials");
		}
		
		UserDetails userDetails = this.myUserDetailsService.loadUserByUsername(authRequest.getUserName());
		return ResponseEntity.ok(new AuthResponse(this.jwtUtil.generateToken(userDetails)));
	}
	
}

package com.geekyants.controller;


import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.geekyants.config.JwtTokenUtil;
import com.geekyants.dto.JwtRequest;
import com.geekyants.dto.JwtResponse;
import com.geekyants.dto.UserDTO;
import com.geekyants.entity.User;
import com.geekyants.service.LogService;
import com.geekyants.service.UserDetailsServiceImpl;

@RestController
@CrossOrigin
@RequestMapping("/api/user")
public class JwtAuthenticationController {
	
	private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationController.class);

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UserDetailsServiceImpl userService;
	
	@Autowired
	private LogService logService;

	/**
	 * This method is responsible for authenticate username & password for providing back the JWT token
	 */
	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
		
		logger.info(logService.formatLogRequest("/authenticate", authenticationRequest, null).toString());
		
		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
		final UserDetails userDetails = userService
				.loadUserByUsername(authenticationRequest.getUsername());
		final String token = jwtTokenUtil.generateToken(userDetails);
		JwtResponse jwtResponse = new JwtResponse(token);
		
		logger.info(logService.formatLogResponse("/authenticate", jwtResponse).toString());
		
		return ResponseEntity.ok(jwtResponse);
	}
	
	/**
	 * This method is responsible for registering new users
	 */
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<?> saveUser(@RequestBody UserDTO user) throws Exception {
		
		logger.info(logService.formatLogRequest("/register", user, null).toString());
		
		User userRes = userService.save(user);
		
		logger.info(logService.formatLogResponse("/register", userRes.getNoteList()).toString());
		
		Map<String, String> resmap = new HashMap<>();
		resmap.put("username", userRes.getUsername());
		resmap.put("message", "regsitration successful");
		resmap.put("notelist", null);
		
		return ResponseEntity.ok(resmap);
		
	}

	/**
	 * This method is responsible for authenticate users, this method is used by createAuthenticationToken(...) method
	 */
	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
}
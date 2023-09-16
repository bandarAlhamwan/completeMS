package com.security.controller;

import java.security.Principal;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.security.config.AuthRequest;
import com.security.config.JwtService;
import com.security.entity.UserCredential;
import com.security.service.UserCredentialService;

import util.Authorization;

@RestController
@CrossOrigin("*")
public class SecurityDemoController {

	
	@Autowired
	private UserCredentialService service;

	@Autowired
	private JwtService jwtService;

	@Autowired
	private AuthenticationManager authenticationManager;

	
	@PostMapping("/login")
	public Map<String, Object> login(@RequestBody AuthRequest authRequest) {
		Map<String, Object> response = new HashMap<>();
		
		Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword()));
		
		response.put("JWT", jwtService.generateToken(authRequest.getUserName()));
		
		Collection<? extends GrantedAuthority> authorities = authenticate.getAuthorities();
		String stringauthorities = authorities.stream().map(a -> a.getAuthority().toUpperCase()).collect(Collectors.joining(","));
		
		response.put("Authorities", stringauthorities);
		
		if(authenticate.isAuthenticated()) {
			return response;
		}else {
		  throw new UsernameNotFoundException("Authentication Faild ! ");
		}
	}

	@GetMapping("/welcome")
	public String Welcome() {
		return "Welcome to java online course";
	}


	
	@GetMapping("Authentication")
	public Authentication getAuthentication(Authentication authentication) {
		return authentication;

	}

	@GetMapping("/principal")
	public Principal principal(Principal principal) {
		return principal;
	}
	
	@GetMapping("passwordEncoder")
	public String passowrdEncoder(@RequestParam(name = "raw", required = false) String raw, 
			@RequestParam(name = "encode", required = false) String encode) {
		
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
	
		if(raw != null) {
			return encoder.encode(raw);
		}else {
			bCryptPasswordEncoder.encode(encode);
		}
		return encoder.encode(raw);
	}
}

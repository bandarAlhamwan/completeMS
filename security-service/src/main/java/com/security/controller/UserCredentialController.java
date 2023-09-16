package com.security.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.security.entity.UserCredential;
import com.security.service.UserCredentialService;

@RestController
public class UserCredentialController {

	@Autowired
	private UserCredentialService credentialService;
	
	@Autowired
	private AuthenticationManager authenticationManager ;

	@PostMapping("/register")
	public UserCredential createUser(@RequestBody UserCredential userCredential) {
		return credentialService.createUser(userCredential);
	}
	
	@GetMapping("/userCredential")
	public List<UserCredential> listUserCredential() {
		return credentialService.listUserCredential();
	}

	@GetMapping("/token")
	public String getToken(@RequestBody UserCredential userCredential) {
		
		Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userCredential.getName(), userCredential.getPassword()));
		if(authenticate.isAuthenticated()) {
			return credentialService.generateToken(userCredential.getName());
		}else {
			throw new RuntimeException("invalid access");
		}
		
	}

	@GetMapping("/validate")
	public String validateToken(@RequestParam("token") String token) {
		credentialService.validateToken(token);
		return "Token Is Valid";
	}

}

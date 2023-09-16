package com.security.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.security.config.JwtService;
import com.security.entity.UserCredential;
import com.security.repository.UserCredentialRepository;

@Service
public class UserCredentialService {

	public static final String DEFAULT_ROLE = "ROLE_DEFAULT";

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserCredentialRepository credentialRepository;

	@Autowired
	private JwtService jwtService;

	public UserCredential createUser(UserCredential userCredential) {
		userCredential.setRawPassword(userCredential.getPassword());
		userCredential.setPassword(passwordEncoder.encode(userCredential.getPassword()));
		userCredential.setRoles(DEFAULT_ROLE);
		return credentialRepository.save(userCredential);
	}

	public List<UserCredential> listUserCredential() {
		return credentialRepository.findAll();
	}

	public String generateToken(String username) {
		return jwtService.generateToken(username);
	}

	public void validateToken(String token) {
		jwtService.validateToken(token);
	}

}

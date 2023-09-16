package com.security.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.security.entity.UserCredential;
import com.security.repository.UserCredentialRepository;

@Service
public class UserCredentialDetailsService implements UserDetailsService{

	@Autowired
	private UserCredentialRepository userCredentialRepository;
		
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<UserCredential> userCredential = userCredentialRepository.findByName(username);
		
		return userCredential.map(UserCredentialDetails::new).orElseThrow(()-> new UsernameNotFoundException(username+" not found in system"));
	}

}

package com.role;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("role")
public class RoleController {

	@Autowired
	private RoleRepository repo;
	
	@Value("${server.port}")
	private String port;
	
	@GetMapping()
	private String getPort() {
		return port;
	}
	
	
	@GetMapping("{username}")
	public String findRoleByUserName(@PathVariable(name = "username") String username) {
		 List<Role> findByuserName = repo.findByuserName(username);
		 
		 String roles = findByuserName.stream()
			      .map(n -> "ROLE_" + n.getRole().toUpperCase())
			      .collect(Collectors.joining(","));
		 return roles;
	}
}

package com.role.user;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class EmployeeController {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@GetMapping("all")
	public List<Employee> getAllUsers() {
		return employeeRepository.findAll();
	}
	
	@GetMapping("{username}")
	public Optional<Employee> getUser(@PathVariable("username") String username) {
		return employeeRepository.findByUserName(username);
	}
}

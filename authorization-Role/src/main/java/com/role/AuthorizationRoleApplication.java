package com.role;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import com.role.user.Employee;
import com.role.user.EmployeeRepository;

import jakarta.annotation.PostConstruct;

@SpringBootApplication
@EnableDiscoveryClient
public class AuthorizationRoleApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthorizationRoleApplication.class, args);
	}

	
	
	@Autowired
	private RoleRepository repo;
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@PostConstruct
	public void init() {
		List<Role> roles = Arrays.asList(
				new Role("bandar", "EMPLOYEE_ADD"),
				new Role("bandar", "EMPLOYEE_UPDATE"),
				new Role("bandar", "EMPLOYEE_LIST"),
				new Role("bandar", "EMPLOYEE_FIND_ID"),
				new Role("bandar", "EMPLOYEE_DELETE"),
				new Role("bandar", "EMPLOYEE_PAGE"),
				new Role("bandar", "text"),
				new Role("deema", "welcome")

				);
		
		List<Employee> employees = Arrays.asList(
				new Employee("bandar", "HR", 0, "bandar@gmail.com", "bandar", "$2a$10$fAz/UOXmpnggj6vDsW0gEuaVT9yv.K4J0npNKO8EJRn3U9RzQT.1i", ""),
				new Employee("deema", "LOW", 0, "deema@gmail.com", "deema", "$2a$10$GDTEKPHAzVgBEqmgunBo7.GjWDmi7y8nR0YlSJ5zxX9hPwkHZjYum", "")
				);
		
		employeeRepository.saveAll(employees);
		repo.saveAll(roles);
	}
}

package com.role.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String dept;
	private double salary;
	private String email;
	private String userName;
	private String password;
	private String roles; // ROLE_HR , ROLE_MANAGER
	
	
	public Employee(String name, String dept, double salary, String email, String userName, String password,
			String roles) {
		super();
		this.name = name;
		this.dept = dept;
		this.salary = salary;
		this.email = email;
		this.userName = userName;
		this.password = password;
		this.roles = roles;
	}
	
	
}

package EMS;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.security.crypto.password.PasswordEncoder;

import EMS.Entity.Employee;
import EMS.Repository.EmployeeRepository;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import jakarta.annotation.PostConstruct;

@SpringBootApplication()
@OpenAPIDefinition(info = @Info(title = "Employee Management System", version = "v 3.0", description = "this is swagger documentation"))
@EnableDiscoveryClient
public class SpringSecurityAuthenticationApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityAuthenticationApplication.class, args);
	}

	@Autowired
	private EmployeeRepository repo;

	//@PostConstruct
	public void init(PasswordEncoder passwordEncoder) {
		List<Employee> empList = Arrays.asList(
				new Employee("bandar", "HR", 2300.5, "Bandar@gmail.com", "bandar", passwordEncoder.encode("bandar"), "ROLE_HR,ROLE_ADMIN"),
				new Employee("bandar", "low", 2300.5, "deema@gmail.com", "deema", passwordEncoder.encode("deema"), "ROLE_EMPLOYEE"));
		
		repo.saveAll(empList);
	}
}

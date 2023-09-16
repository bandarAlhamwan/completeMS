package EMS.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import EMS.Entity.Employee;
import EMS.Exception.EmployeeNotFoundException;
import EMS.Repository.EmployeeRepository;

import java.util.List;

@Service
public class EmployeeService {

	public static final String DEFAULT_ROLE = "ROLE_EMPLOYEE";

	
	@Autowired
	private EmployeeRepository repository;


	public Employee createNewEmployee(Employee employee) {
		employee.setPassword("123456");
		employee.setRoles(DEFAULT_ROLE);
		return repository.save(employee);
	}

	public List<Employee> getEmployees() {
		return repository.findAll();
	}

	public Employee getEmployeeById(Integer id) {
		return repository.findById(id).orElseThrow(() -> new EmployeeNotFoundException("Employee Not Found with id " + id));
	}

	public Employee updateEmployee(Employee employee, int id) {
		Employee employeeById = getEmployeeById(id);
		employeeById = employee;
		return repository.save(employeeById);
	}
	
	
	public Page<Employee> findPagenation(int pageNumber, int pageSize, String sort, String field) {
		Page<Employee> page;

		if(sort.equals("ASC")) {
			page = repository.findAll(PageRequest.of(pageNumber -1, pageSize, Direction.ASC, field));
		}else {
			page = repository.findAll(PageRequest.of(pageNumber -1, pageSize, Direction.DESC, field));
		}
		
		return page;
	}

	public Employee findByEmail(String email) {
		return repository.findByEmailIgnoreCase(email);
	}
	
	public Employee findByuserName(String username) {
		return repository.findByuserName(username);
	}

	
	public Employee changeRoleOfEmployee(Employee employee) {
		Employee existingEmployee = getEmployeeById(employee.getId());
		existingEmployee.setRoles(employee.getRoles());
		return repository.save(existingEmployee);
	}


	public void deleteEmployeeById(int id) {
		getEmployeeById(id);
		repository.deleteById(id);
	}
	
}

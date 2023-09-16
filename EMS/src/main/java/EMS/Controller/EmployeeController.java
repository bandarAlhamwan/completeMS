package EMS.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import EMS.Entity.Employee;
import EMS.Service.EmployeeService;

@RestController
@RequestMapping("employee")
public class EmployeeController {

	
	@Autowired
	private EmployeeService service;
	
	@GetMapping("/list")
	public List<Employee> getAll() {
		return service.getEmployees();
	}
	
	@PostMapping("/add")
	public Employee onboardNewEmployee(@RequestBody Employee employee) {
		return service.createNewEmployee(employee);
	}

	@PostMapping("/create2")
	public String onboardNewEmployee1(@RequestBody List<Employee> ListEmployee) {

		for (Employee emp : ListEmployee) {
			service.createNewEmployee(emp);
		}
		return " 2 Employees added";
	}
	
	@GetMapping("/{id}")
	public Employee getEmployeeById(@PathVariable Integer id) {
		return service.getEmployeeById(id);
	}
	
	@PutMapping("/update/{id}")
	public Employee updateEmployee(@RequestBody Employee employee, @PathVariable(name = "id") int id) {
		return service.updateEmployee(employee, id);
	}
	
	@DeleteMapping("/delete/{id}")
	public String deleteEmployeeById(@PathVariable(name = "id") int id) {
		service.deleteEmployeeById(id);
		return "Employee with Id: " +id + " has been deleted sucessfully";
	}
	
	@GetMapping("/page")
	public Page<Employee> findPagenation(
			@RequestParam (name = "pageNumber", required = false, defaultValue = "1") int pageNumber,
			@RequestParam (name = "pageSize", required = false, defaultValue = "3") int pageSize,
			@RequestParam (name = "sort", required = false, defaultValue = "ASC") String sort,
			@RequestParam (name = "field", required = false, defaultValue = "id") String field
			) {

		return service.findPagenation(pageNumber,pageSize,sort, field);
	}
	
	@GetMapping("/CustomPage")
	public Map<String, Object> findCustomPagenation(
			@RequestParam (name = "pageNumber", required = false, defaultValue = "10") int pageNumber,
			@RequestParam (name = "pageSize", required = false, defaultValue = "5") int pageSize,
			@RequestParam (name = "sort", required = false, defaultValue = "ASC") String sort,
			@RequestParam (name = "field", required = false, defaultValue = "id") String field
			) {

		Page<Employee> findCustomPagenation = service.findPagenation(pageNumber,pageSize,sort, field);
		Map<String, Object> response = new HashMap<>();
	    response.put("employees", findCustomPagenation.getContent());
	    response.put("currentPage", findCustomPagenation.getNumber() + 1);
	    response.put("totalItems", findCustomPagenation.getTotalElements());
	    response.put("totalPages", findCustomPagenation.getTotalPages());
		return response;
	}

	
	@GetMapping("/checkEmail/{email}")
	public Employee checkEmail(@PathVariable(name = "email") String email) {
		
		// to remove white space
		email = email.replaceAll("\\s+","");

		Employee employee = service.findByEmail(email);

		return employee;
	}
	
	@CrossOrigin("*")
	@GetMapping("/checkUsername/{username}")
	public Employee checkUserName(@PathVariable(name = "username") String username) {
		
		//trim() : to remove white space from beginning and end
		// replaceAll(" +", " ") : to remove duplicated whitespace 
		username = username.trim().replaceAll(" +", " ");;
		System.out.println(username);
		
		Employee employee = service.findByuserName(username);

		return employee;
	}
}

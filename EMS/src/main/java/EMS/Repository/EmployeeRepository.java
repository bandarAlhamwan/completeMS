package EMS.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.PathVariable;

import EMS.Entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>{

	Optional<Employee> findByUserName(String userName);
	
	@Query("SELECT e FROM Employee e WHERE e.email = ?1")
	Employee bandar(@PathVariable String email);
	
	Employee findByEmailIgnoreCase(String email);
	
	Employee findByuserName(String username);
}

package suraj_pkg.cruddemo.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import suraj_pkg.cruddemo.entity.Employee;
import suraj_pkg.cruddemo.repo.EmployeeRepository;
import suraj_pkg.cruddemo.service.EmployeeService;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {
		
		private EmployeeRepository employeeRepo;
		//private EmployeeService employeeService;
		//Constructor Injection
		public EmployeeRestController(/*EmployeeService theService*/ 
				EmployeeRepository theRepo)
		{
			//employeeService=theService;
			employeeRepo=theRepo;
		}
		@GetMapping("/employees")
		public List<Employee> findAll()
		{
			return employeeRepo.findAll();//employeeService.findAll();
		}
		// add mapping for GET /employees/{employeeId}
		
		@GetMapping("/employees/{employeeId}")
		public Employee getEmployee(@PathVariable int employeeId) {
			Optional<Employee> op=employeeRepo.findById(employeeId);
			
			Employee theEmployee ;//employeeService.findById(employeeId);
			if(op.isPresent())
			{
				theEmployee=op.get();
			}
			else {
				throw new RuntimeException("Employee id not found - " + employeeId);
			}
			
			return theEmployee;
		}
		
		// add mapping for POST /employees - add new employee
		
		@PostMapping("/employees")
		public Employee addEmployee(@RequestBody Employee theEmployee) {
			
			// also just in case they pass an id in JSON ... set id to 0
			// this is to force a save of new item ... instead of update
			
			theEmployee.setId(0);
				employeeRepo.save(theEmployee);
			//employeeService.save(theEmployee);
			
			return theEmployee;
		}
		
		// add mapping for PUT /employees - update existing employee
		
		@PutMapping("/employees")
		public Employee updateEmployee(@RequestBody Employee theEmployee) {
			
			employeeRepo.save(theEmployee);
			//employeeService.save(theEmployee);
			
			return theEmployee;
		}
		
		// add mapping for DELETE /employees/{employeeId} - delete employee
		
		@DeleteMapping("/employees/{employeeId}")
		public String deleteEmployee(@PathVariable int employeeId) {
			Optional<Employee> obj=employeeRepo.findById(employeeId);
			Employee tempEmployee ;
					//employeeService.findById(employeeId);
			
			// throw exception if null
			if(obj.isPresent())
			{
				tempEmployee=obj.get();
			}
			else{
				throw new RuntimeException("Employee id not found - " + employeeId);
			}
			employeeRepo.deleteById(employeeId);
			//employeeService.deleteById(employeeId);
			
			return "Deleted employee id - " + employeeId;
		}

}


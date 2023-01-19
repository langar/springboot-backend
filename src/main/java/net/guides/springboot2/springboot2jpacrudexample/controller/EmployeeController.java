package net.guides.springboot2.springboot2jpacrudexample.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import net.guides.springboot2.springboot2jpacrudexample.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.guides.springboot2.springboot2jpacrudexample.exception.ResourceNotFoundException;
import net.guides.springboot2.springboot2jpacrudexample.model.Employee;
import net.guides.springboot2.springboot2jpacrudexample.repository.IEmployeeRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class EmployeeController {
	@Autowired
	private IEmployeeRepository IEmployeeRepository;
	@Autowired
	private EmployeeService employeeService;

	@GetMapping("/employees") // Get All
	public List<Employee> getAllEmployees() {
		return employeeService.getEmployeeList();
	}

	@GetMapping("/employees/{id}") // Get One
	public ResponseEntity<Optional<Employee>> getEmployeeById(@PathVariable(value = "id") Long employeeId)
			throws ResourceNotFoundException {

		Optional<Employee> employee = employeeService.findEmployeeById(employeeId);

		return ResponseEntity.ok().body(employee);
	}

	@PostMapping("/employees") // Create
	public Employee createEmployee(@Valid @RequestBody Employee employee) {

		return employeeService.creeerEmployer(employee);
	}

	@PutMapping("/employees/{id}") // Update
	public ResponseEntity<Employee> updateEmployee(@PathVariable(value = "id") Long employeeId,
			@Valid @RequestBody Employee employeeDetails) throws ResourceNotFoundException {

		return ResponseEntity.ok(employeeService.updateEmployee(employeeId, employeeDetails));
	}

	@DeleteMapping("/employees/{id}") // Delete
	public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") Long employeeId)
			throws ResourceNotFoundException {

		employeeService.supprimerEmployee(employeeId);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}

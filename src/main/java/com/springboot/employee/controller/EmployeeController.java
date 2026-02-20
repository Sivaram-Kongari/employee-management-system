package com.springboot.employee.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.springboot.employee.dto.EmployeeDTO;
import com.springboot.employee.entity.Employee;
import com.springboot.employee.service.EmployeeService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;


	// Bulk Insert
	@PostMapping("/bulk")
	public List<Employee> addAllEmployees(@Valid @RequestBody List<EmployeeDTO> employeeDTO) {

		List<Employee> insert = employeeService.insertAllEmployees(employeeDTO);
		return insert;
	}

	@PostMapping("/addEmployee")
	public Employee add(@RequestBody @Valid EmployeeDTO employeeDTO) {

		Employee insert = employeeService.insert(employeeDTO);
		return insert;
	}
	@PutMapping("/updateEmployee/{id}")
	public Employee modify(@PathVariable Long id, @RequestBody @Valid EmployeeDTO employeeDTO) {

		Employee update = employeeService.update(id, employeeDTO);
		return update;
	}
	@DeleteMapping("/deleteEmployee/{id}")
	public void remove(@PathVariable Long id) {

		employeeService.delete(id);
	}
	@GetMapping("/getById/{id}")
	public Employee fetchEmployeeById(@PathVariable Long id) {

		Employee employeeById = employeeService.getEmployeeById(id);
		return employeeById;
	}

	//Pagination & Sorting
	@GetMapping("/getEmployee")
	public Page<Employee> fetchEmployee(
			@RequestParam int page, 
			@RequestParam int size, 
			@RequestParam String sortBy, 
			@RequestParam String direction) {

		Page<Employee> employee = employeeService.getEmployee(page, size, sortBy, direction);
		return employee;
	}
}
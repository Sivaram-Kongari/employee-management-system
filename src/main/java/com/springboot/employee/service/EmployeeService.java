package com.springboot.employee.service;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import com.springboot.employee.dto.EmployeeDTO;
import com.springboot.employee.entity.Employee;
import com.springboot.employee.exception.EmployeeNotFoundException;
import com.springboot.employee.repo.EmployeeRepository;

@Component
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private ModelMapper modelMapper;

	public List<Employee> insertAllEmployees(List<EmployeeDTO> employeeDTO) {

		List<Employee> employees = employeeDTO.stream()
				.map(dto -> modelMapper.map(dto, Employee.class))
				.collect(Collectors.toList());

		List<Employee> saveAll = employeeRepository.saveAll(employees);
		return saveAll;
	}
	public Employee insert(EmployeeDTO employeeDTO) {

		//		Employee emp = new Employee();
		//		emp.setName(employeeDTO.getName());
		//		emp.setSalary(employeeDTO.getSalary());
		//		emp.setDepartment(employeeDTO.getDepartment());
		//		emp.setEmail(employeeDTO.getEmail());
		//		emp.setPhone(employeeDTO.getPhone());

		Employee emp = modelMapper.map(employeeDTO, Employee.class);
		Employee save = employeeRepository.save(emp);
		return save;
	}
	public Employee update(Long id, EmployeeDTO employeeDTO) {

		Employee existingEmployee = employeeRepository.findById(id)
				.orElseThrow(() -> new EmployeeNotFoundException("Employee not found with the given id : "+id));
		modelMapper.map(employeeDTO, existingEmployee);
		Employee save = employeeRepository.save(existingEmployee);
		return save;
	}
	public void delete(Long id) {

		Employee deleteId = employeeRepository.findById(id)
				.orElseThrow(() -> new EmployeeNotFoundException("Employee not found with the given id : "+id));
		employeeRepository.delete(deleteId);
	}
	public Employee getEmployeeById(Long id) {

		Employee byId = employeeRepository.findById(id).orElseThrow(() -> 
		new EmployeeNotFoundException("Employee Not Found with id : "+id));
		return byId;
	}

	//Pagination & Sorting
	public Page<Employee> getEmployee(int page, int size, String sortBy, String direction) {

		//		Sort sort;
		//
		//		if (direction.equalsIgnoreCase("asc")) {
		//
		//			sort = Sort.by(sortBy).ascending();
		//
		//		} else {
		//
		//			sort = Sort.by(sortBy).descending();
		//		}
		//

		Sort sort = direction.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
		Pageable pageable = PageRequest.of(page, size, sort);
		Page<Employee> all = employeeRepository.findAll(pageable);
		return all;
	}
}
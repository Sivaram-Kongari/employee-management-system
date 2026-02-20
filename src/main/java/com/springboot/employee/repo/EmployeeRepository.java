package com.springboot.employee.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.springboot.employee.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}

package com.springboot.employee.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeDTO {

	@NotBlank(message = "Name should not be empty")
	private String name;

	private double salary;

	@Email(message = "Email is required")
	@NotBlank(message = "Email should not be empty")
	private String email;

	@NotBlank(message = "Phone number is required")
	@Pattern(regexp = "^[0-9]{10}$", message = "Phone number must be 10 digits")
	private String phone;

	private String department;
}

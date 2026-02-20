package com.springboot.employee.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponse {

	private int status;
	private String msg;

}

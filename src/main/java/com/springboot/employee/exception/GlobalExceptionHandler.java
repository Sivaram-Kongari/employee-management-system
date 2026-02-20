package com.springboot.employee.exception;

import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import com.springboot.employee.dto.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

	// For Single 
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<HashMap<String, String>> handleValidationException(MethodArgumentNotValidException ex) {

		HashMap<String, String> errors = new HashMap<>();

		//		for (FieldError error : ex.getBindingResult().getFieldErrors()) {
		//			errors.put(error.getField(), error.getDefaultMessage());
		//		}


		ex.getBindingResult().getFieldErrors().forEach(error -> {
			errors.put(error.getField(), error.getDefaultMessage());
		});
		ResponseEntity<HashMap<String, String>> rs = new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);  
		return rs;
	}

	// ✅ for List<@Valid DTO> validation (bulk case)
	@ExceptionHandler(HandlerMethodValidationException.class)
	public ResponseEntity<Map<String, String>> handleHandlerMethodValidation(HandlerMethodValidationException ex, HttpServletRequest request) {

		Map<String, String> errors = new HashMap<>();

		ex.getAllErrors().forEach(error -> {
			if (error instanceof FieldError fieldError) {
				errors.put(fieldError.getField(), fieldError.getDefaultMessage());
			} else {
				errors.put("error", error.getDefaultMessage());
			}
		});

		return ResponseEntity.badRequest().body(errors);
	}
	@ExceptionHandler(EmployeeNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleEmployeeNotFoundException(EmployeeNotFoundException exception) {

		ErrorResponse er = new ErrorResponse();
		er.setStatus(HttpStatus.NOT_FOUND.value());
		er.setMsg(exception.getMessage());

		ResponseEntity<ErrorResponse> rs = new ResponseEntity<>(er, HttpStatus.NOT_FOUND);
		return rs;
	}
}
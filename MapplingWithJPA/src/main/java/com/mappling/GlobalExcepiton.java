package com.mappling;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;

@OpenAPIDefinition
@RestControllerAdvice
public class GlobalExcepiton {
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> methodArgumentExcepiton(MethodArgumentNotValidException exception) {
		Map<String, String> map = new HashMap<String, String>();
		exception.getBindingResult().getAllErrors().forEach(error -> {
			String field = ((FieldError) error).getField();
			String defaultMessage = error.getDefaultMessage();
			map.put(field, defaultMessage);
		});
		return (ResponseEntity<?>) map;
	}
	
	
}
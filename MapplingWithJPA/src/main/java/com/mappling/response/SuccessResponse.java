package com.mappling.response;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class SuccessResponse {
	private String message; 
	private Object data; 
	private HttpStatus httpStatus;
	private LocalDateTime localDateTime;

}

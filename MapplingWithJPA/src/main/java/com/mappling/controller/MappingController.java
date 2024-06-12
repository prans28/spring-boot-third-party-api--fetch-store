package com.mappling.controller;

import java.io.IOException;
import java.lang.ref.Cleaner.Cleanable;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.util.List;

import org.apache.catalina.startup.ClassLoaderFactory.RepositoryType;
import org.hibernate.grammars.hql.HqlParser.RootEntityContext;
import org.springframework.boot.actuate.autoconfigure.observation.ObservationProperties.Http;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mappling.DTO.EmployeeDTO;
import com.mappling.entity.Employee;
import com.mappling.entity.Post;
import com.mappling.response.SuccessResponse;
import com.mappling.service.EmployeeService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
@Slf4j
public class MappingController {
	private final EmployeeService employeeSerivce;

	@GetMapping("/api")
	public ResponseEntity<SuccessResponse> dummy1() {
		return ResponseEntity.ofNullable(SuccessResponse.builder().message("ok")
				.data(Employee.builder().employeeId(1).employeeName("prnav").employeeAge("22").employeePhone("xyz")
						.build())
				.localDateTime(LocalDateTime.now()).httpStatus(HttpStatus.HTTP_VERSION_NOT_SUPPORTED).build());
	}

	@GetMapping("/home")
	public ResponseEntity<SuccessResponse> dummy() {
		return ResponseEntity.ofNullable(SuccessResponse.builder().message("ok").data(null)
				.localDateTime(LocalDateTime.now()).httpStatus(HttpStatus.HTTP_VERSION_NOT_SUPPORTED).build());
	}
     
	@PostMapping("/create")
	public ResponseEntity<SuccessResponse> createUser(@RequestBody EmployeeDTO employeeDTO) {
		System.out.println(employeeDTO.getEmployeeSalary());
		String message = employeeSerivce.createEmployee(employeeDTO);
		log.info("Employee has been updated inserte wity id  " + message);
		return ResponseEntity.ofNullable(SuccessResponse.builder().data(message).httpStatus(HttpStatus.OK)
				.localDateTime(LocalDateTime.now()).message("employee has been inserted").build());
	}

	@GetMapping("/posts")
	public ResponseEntity<SuccessResponse> getCovidData() throws IOException, InterruptedException {
        var url = "https://jsonplaceholder.typicode.com/posts";
        var request = HttpRequest.newBuilder().GET().uri(URI.create(url)).build();
        var client = HttpClient.newBuilder().build();
        var response = client.send(request, HttpResponse.BodyHandlers.ofString());
        int statusCode = response.statusCode();
        if (statusCode == 200) {
            ObjectMapper objectMapper = new ObjectMapper();
            List<Post> posts = objectMapper.readValue(response.body(), new TypeReference<List<Post>>() {});
           String message =  employeeSerivce.saveAllPost(posts);
        }
        return ResponseEntity.ofNullable(SuccessResponse.builder()
                .httpStatus(HttpStatus.valueOf(statusCode))
                .message(Integer.toString(statusCode))
                .data(response.body())
                .localDateTime(LocalDateTime.now())
                .build());
    }
	
	
  
}

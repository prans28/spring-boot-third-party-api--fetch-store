package com.mappling.service;

import java.util.List;

import com.mappling.DTO.EmployeeDTO;
import com.mappling.entity.Post;

public interface EmployeeService {

	String createEmployee(EmployeeDTO employeeDTO);

	String saveAllPost(List<Post> posts);

}

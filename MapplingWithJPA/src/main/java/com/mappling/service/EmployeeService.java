package com.mappling.service;
import java.io.IOException;
import java.util.List;
import com.mappling.DTO.EmployeeDTO;
import com.mappling.DTO.PostDTO;
import com.mappling.entity.Employee;
import com.mappling.entity.Post;

import jakarta.servlet.http.HttpServletResponse;

public interface EmployeeService {

	String createEmployee(EmployeeDTO employeeDTO);

	String saveAllPost(List<Post> posts);

	List<PostDTO> findAll(Integer offset, Integer pageRequired);

	List<Employee> getAllDataInExcel();

	String save(List<PostDTO> value , HttpServletResponse httpServletRespose) throws IOException;

}

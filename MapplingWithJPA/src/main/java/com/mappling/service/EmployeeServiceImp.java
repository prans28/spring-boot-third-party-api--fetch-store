package com.mappling.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mappling.DTO.EmployeeDTO;
import com.mappling.entity.Employee;
import com.mappling.entity.Post;
import com.mappling.repostory.EmployeeRepository;
import com.mappling.repostory.PostRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class EmployeeServiceImp implements EmployeeService {
	private final EmployeeRepository employeeRepository;
	private final PostRepository postRepository;

	@Override
	public String createEmployee(EmployeeDTO employeeDTO) {
		
		System.out.println(employeeDTO.getEmployeeAge());
		Employee save = employeeRepository.save(Employee.builder().employeeAge(employeeDTO.getEmployeeAge())
				.employeeEmail(employeeDTO.getEmployeeEmail()).employeeName(employeeDTO.getEmployeeName())
				.employeePhone(employeeDTO.getEmployeePhone()).employeeSalary(employeeDTO.getEmployeeSalary()).build());
		return Integer.toString(save.getEmployeeId());
	}

	@Override
	public String saveAllPost(List<Post> posts) {
		postRepository.saveAll(posts);
		return "success";
	}

}

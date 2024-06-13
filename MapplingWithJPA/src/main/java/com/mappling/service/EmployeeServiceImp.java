package com.mappling.service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.mappling.DTO.EmployeeDTO;
import com.mappling.DTO.PostDTO;
import com.mappling.entity.Employee;
import com.mappling.entity.Post;
import com.mappling.repostory.EmployeeRepository;
import com.mappling.repostory.PostRepository;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
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

	@Override
	public List<PostDTO> findAll(Integer offset, Integer pageRequired) {
		Page<Post> all = postRepository.findAll(PageRequest.of(pageRequired, offset));
		if (all.isEmpty()) {
			return null;
		}
		List<PostDTO> collect = all.stream().map(e -> PostDTO.builder().id(e.getId()).userId(e.getUserId())
				.title(e.getTitle()).body(e.getBody()).build()).collect(Collectors.toList());
		return collect;
	}

	@Override
	public List<Employee> getAllDataInExcel() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String save(List<PostDTO> value, HttpServletResponse httpServletRespose) throws IOException {
		List<Post> collect = value.stream().map(
				e -> Post.builder().id(e.getId()).userId(e.getUserId()).body(e.getBody()).title(e.getTitle()).build())
				.collect(Collectors.toList());
		postRepository.saveAll(collect);
		List<Post> all = postRepository.findAll();
		List<PostDTO> collect2 = all.stream().map(e -> PostDTO.builder().id(e.getId()).userId(e.getUserId())
				.title(e.getTitle()).body(e.getBody()).build()).collect(Collectors.toList());
		HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
		HSSFSheet sheet = hssfWorkbook.createSheet("post");
		HSSFRow cell = sheet.createRow(0);
		cell.createCell(0).setCellValue("userId");
		cell.createCell(1).setCellValue("id");
		cell.createCell(2).setCellValue("title");
		cell.createCell(3).setCellValue("body");

		int count = 1;
		for (PostDTO post : collect2) {
			HSSFRow cell1 = sheet.createRow(count);
			cell1.createCell(0).setCellValue(post.getUserId());
			cell1.createCell(1).setCellValue(post.getId());
			cell1.createCell(2).setCellValue(post.getTitle());
			cell1.createCell(5).setCellValue(post.getBody());
			count++;
		}
		ServletOutputStream outputStream = httpServletRespose.getOutputStream();
		hssfWorkbook.write(outputStream);
		hssfWorkbook.close();

		return "done";
	}
}

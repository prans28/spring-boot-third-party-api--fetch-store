package com.mappling.DTO;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class EmployeeDTO {
    @Size(min = 10 ,max = 20 ,message = "enter proper name like name not character is allowed")
	private String employeeName;
    @Size(min = 10 ,max = 20 ,message = "enter proper name like name not character is allowed")
    private String employeeAge;
    @Size(min = 10 ,max = 20 ,message = "enter proper name like name not character is allowed")
    private String employeeSalary;
    @Size(min = 10 ,max = 20 ,message = "follow the proper convension")
    @Pattern(regexp = "^[a-zA-Z0-9_.#]+@[a-zA-Z]+\\.[a-z]{2,}$")
    private String employeeEmail;
    @Size(min = 10)
    @Pattern(regexp = "[0-9]{10}")
    private String employeePhone;
}

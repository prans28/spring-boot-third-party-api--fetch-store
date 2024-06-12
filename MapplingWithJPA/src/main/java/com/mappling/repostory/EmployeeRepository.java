package com.mappling.repostory;
import org.springframework.data.jpa.repository.JpaRepository;
import com.mappling.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee , Integer> {
	

}

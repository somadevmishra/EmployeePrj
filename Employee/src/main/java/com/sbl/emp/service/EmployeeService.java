package com.sbl.emp.service;

import java.util.List;

import com.sbl.emp.model.Employee;


public interface EmployeeService {

	List<Employee> getAllEmployees();
	Employee getEmployee(Long empId);
}

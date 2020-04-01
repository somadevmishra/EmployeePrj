package com.sbl.emp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbl.emp.model.Employee;
import com.sbl.emp.repo.EmployeeRepository;
import com.sbl.emp.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;
	
	public EmployeeServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Employee> getAllEmployees() {
		// TODO Auto-generated method stub
		return employeeRepository.findAll();
	}

	@Override
	public Employee getEmployee(Long empId) {
		// TODO Auto-generated method stub
		return employeeRepository.findAllByEmpId(empId).get(0);
	}

}

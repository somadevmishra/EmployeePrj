package com.sbl.emp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sbl.emp.model.User;
import com.sbl.emp.model.Employee;
import com.sbl.emp.service.EmpUserService;
import com.sbl.emp.service.EmployeeService;


@RestController
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;
	
	@Autowired
	EmpUserService empUserService;
	
	public EmployeeController() {
		// TODO Auto-generated constructor stub
	}

	@GetMapping("/")
	public String homePage(Model model) {
		return "Employee";
	}
	
	@GetMapping(path = "/allemployees", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody ResponseEntity<List<Employee>> getAllEmployees(Model model){
		
		List<Employee> employees = employeeService.getAllEmployees();

		return ResponseEntity.ok(employees)
							
				;//new ResponseEntity<List<Employee>>(employees, HttpStatus.OK);
	}
	
	@GetMapping(path = "/getuser/{emailId}")
	public ResponseEntity<User> getUser(@PathVariable("emailId") String emailId){
		User user = empUserService.getEmpUser(emailId);
		return ResponseEntity.ok(user);
	}
}

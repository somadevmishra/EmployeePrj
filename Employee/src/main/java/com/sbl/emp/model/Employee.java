package com.sbl.emp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "employee")
public class Employee {

	public Employee() {
		// TODO Auto-generated constructor stub
	}

	@Id
	@Column(name = "emp_id")
	Long empId;
	
	@Column(name = "first_name")
	String firstName;
	
	@Column(name = "last_name")
	String lastName;
	
	@Column(name = "status")
	String status;

}

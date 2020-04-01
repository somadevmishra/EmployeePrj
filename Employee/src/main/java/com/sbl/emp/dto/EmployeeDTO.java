package com.sbl.emp.dto;

import lombok.Data;
import lombok.Setter;

@Data
@Setter
public class EmployeeDTO {

	public EmployeeDTO() {
		// TODO Auto-generated constructor stub
	}

	@Setter
	Long empId;
	String firstName;
	String lastName;
}

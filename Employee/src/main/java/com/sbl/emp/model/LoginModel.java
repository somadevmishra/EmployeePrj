package com.sbl.emp.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;

import lombok.Data;

@Data
public class LoginModel {

	public LoginModel() {
		// TODO Auto-generated constructor stub
	}

	@Email
	private String username;
	
	@Min(value = 8, message = "Password must be 8 or more characters")
	private String password;
}

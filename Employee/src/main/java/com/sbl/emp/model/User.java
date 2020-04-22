package com.sbl.emp.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;

import lombok.Data;

@Entity
@Table(name = "user")
@Data
public class User {

	public User() {
		// TODO Auto-generated constructor stub
	}

	@Id
	@Column(name = "Id")
	Long empId;
	
	@Column(name = "Name")
	String empName;
	
	@Column(name = "password")
	@Min(value = 6)
	String password;
	
	@Column(name = "emailid")
	@Email
	String emailId;
	
	@Column(name = "status")
	Boolean status;
	
	@Column(name = "created_on")
	Date createdOn;
	
	@Column(name = "last_modified_on")
	Date lastModifiedOn;
	
	@Column(name = "continous_failed_login")
	Integer continousFailedLogin;
	
	@Column(name = "created_by")
	Long createdBy;
	
	@Column(name = "LAST_MODIFIED_BY")
	Long modifiedBy;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "user_roles",
		joinColumns = @JoinColumn(name = "role_id"),
		inverseJoinColumns = @JoinColumn(name = "user_id")
	)
	Set<Role> roles;
}

package com.sbl.emp.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Entity
@Table(name = "roles")
@Data
public class Role {

	public Role() {
		// TODO Auto-generated constructor stub
	}

	@Id
	@Column(name = "id")
	Long id;

	@NotNull
	@Column(name = "role_name")
	String roleName;

	@Column(name = "description")
	String dsription;

	@Column(name = "created_on")
	Date createdOn;

	@Column(name = "last_modified_on")
	Date lastModifiedOn;

	@Column(name = "created_by")
	Long createdBy;

	@Column(name = "last_modified_by")
	Long lastModifiedBy;

	/*
	 * @ManyToMany(fetch = FetchType.LAZY, mappedBy = "roles") List<EmpUser> users;
	 */

}

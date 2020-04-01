package com.sbl.emp.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sbl.emp.model.Employee;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {

	List<Employee> findAll();
	
	List<Employee> findAllByEmpId(Long empId);
	
}

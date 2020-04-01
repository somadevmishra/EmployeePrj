package com.sbl.emp.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sbl.emp.model.EmpUser;

@Repository
public interface EmpUserRepository extends CrudRepository<EmpUser, Long> {

	EmpUser findByEmailId(String emailId);
}

package com.sbl.emp.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sbl.emp.model.User;

@Repository
public interface EmpUserRepository extends CrudRepository<User, Long> {

	User findByEmailId(String emailId);
}

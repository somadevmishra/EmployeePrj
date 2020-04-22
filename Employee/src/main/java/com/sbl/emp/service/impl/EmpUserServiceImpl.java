package com.sbl.emp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbl.emp.model.User;
import com.sbl.emp.repo.EmpUserRepository;
import com.sbl.emp.service.EmpUserService;

@Service
public class EmpUserServiceImpl implements EmpUserService {

	@Autowired
	private EmpUserRepository empUserRepository;
	
	public EmpUserServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public User getEmpUser(String emailId) {
		// TODO Auto-generated method stub
		return empUserRepository.findByEmailId(emailId);
	}

}

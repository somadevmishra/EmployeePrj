package com.sbl.emp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sbl.emp.model.EmpUser;
import com.sbl.emp.repo.EmpUserRepository;

@Service
public class EmpUserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	EmpUserRepository userRepository;
	
	public EmpUserDetailsServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		EmpUser user = userRepository.findByEmailId(username);
		EmpUserDetails userDetails = new EmpUserDetails(user);
		
		return userDetails;
	}

}

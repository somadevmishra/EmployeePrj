package com.sbl.emp.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.sbl.emp.model.User;
import com.sbl.emp.model.Role;
import com.sbl.emp.util.ApplicationConstants;



public class EmpUserDetails implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	User empUser;
	
	public EmpUserDetails() {
		// TODO Auto-generated constructor stub
	}
	
	public EmpUserDetails(User user) {
		empUser = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		List<GrantedAuthority> autorities = new ArrayList<GrantedAuthority>();
		for(Role role : empUser.getRoles()) {
			autorities.add(new SimpleGrantedAuthority(role.getRoleName()));
		}
		
		return autorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(ApplicationConstants.PASSWORDENCODERSTRENGTH.intValue());
		return encoder.encode(empUser.getPassword());
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return empUser.getEmailId();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return empUser.getStatus().booleanValue();
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return empUser.getStatus().booleanValue();
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return empUser.getStatus().booleanValue();
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return empUser.getStatus().booleanValue();
	}

}

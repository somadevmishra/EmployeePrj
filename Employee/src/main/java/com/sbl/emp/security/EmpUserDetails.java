package com.sbl.emp.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.sbl.emp.model.EmpUser;
import com.sbl.emp.model.Role;


public class EmpUserDetails implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	EmpUser empUser;
	
	public EmpUserDetails() {
		// TODO Auto-generated constructor stub
	}
	
	public EmpUserDetails(EmpUser user) {
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
		return empUser.getPassword();
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

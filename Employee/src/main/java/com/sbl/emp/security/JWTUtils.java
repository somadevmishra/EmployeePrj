package com.sbl.emp.security;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sbl.emp.model.Role;
import com.sbl.emp.model.User;
import com.sbl.emp.service.EmpUserService;
import com.sbl.emp.util.ApplicationConstants;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTUtils {

	@Autowired
	EmpUserService empService;
	
	public JWTUtils() {
		// TODO Auto-generated constructor stub
	}

	public String createToken(String userEmailId) {
		
		User user = empService.getEmpUser(userEmailId);
		Map<String, Object> claims = new HashMap<>();
		claims.put("username", user.getEmailId());
		claims.put("password", user.getPassword());
		Set<Role> roles = user.getRoles();
		List<String> roleNames = roles.stream().map(e -> e.getRoleName()).collect(Collectors.toList());
		claims.put("roles", roleNames);
		
		return Jwts.builder()
		.setSubject(user.getEmailId())
		.setExpiration(new Date(System.currentTimeMillis()+ApplicationConstants.EXPAIRATIONDEADLINE))
		.addClaims(claims)
		.signWith(SignatureAlgorithm.HS256, ApplicationConstants.SECRETKEY)
		.compact()
		;
	}
	
	public boolean validateUser(String token) {
		
		Claims claims = getClaims(token);
		String userEmailId = (String)claims.get("username");
		User user = empService.getEmpUser(userEmailId);
		return user.getEmailId().equals(claims.get("username")) && user.getPassword().equals(claims.get("password"));
	}
	
	
	public boolean validateUser(String token, String userEmailId) {
		User user = empService.getEmpUser(userEmailId);
		Claims claims = getClaims(token);
		return user.getEmailId().equals(claims.get("username")) && user.getPassword().equals(claims.get("password"));
		
	}
	public Claims getClaims(String token) {
		return Jwts.parser().setSigningKey(ApplicationConstants.SECRETKEY).parseClaimsJws(token).getBody();
	}
	
	
}

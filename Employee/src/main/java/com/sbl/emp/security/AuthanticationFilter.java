package com.sbl.emp.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sbl.emp.model.LoginModel;
import com.sbl.emp.service.EmpUserService;

public class AuthanticationFilter extends UsernamePasswordAuthenticationFilter {
	
	@Autowired
	EmpUserService empService;

	@Autowired
	JWTUtils jwtutils;
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		// TODO Auto-generated method stub
		try {
			LoginModel loginModel = new ObjectMapper().readValue(request.getInputStream(), LoginModel.class);
		
		return getAuthenticationManager().authenticate(
				new UsernamePasswordAuthenticationToken(loginModel.getUsername(),
						loginModel.getPassword())
				);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		// TODO Auto-generated method stub
		String userEmailId = ((EmpUserDetails)authResult.getPrincipal()).getUsername();
		/*
		 * User user = empService.getEmpUser(userEmailId); Map<String, Object> claims =
		 * new HashMap<>(); claims.put(SPRING_SECURITY_FORM_USERNAME_KEY,
		 * user.getEmailId()); claims.put(SPRING_SECURITY_FORM_PASSWORD_KEY,
		 * user.getPassword()); claims.put("roles", user.getRoles()); String token =
		 * Jwts.builder() .setSubject(user.getEmailId()) .setExpiration(new
		 * Date(System.currentTimeMillis()+ApplicationConstants.EXPAIRATIONDEADLINE))
		 * .addClaims(claims) .signWith(SignatureAlgorithm.HS256,
		 * ApplicationConstants.SECRETKEY) .compact() ;
		 */
		String token = jwtutils.createToken(userEmailId);
		
		response.addHeader("token", token);
				
	}
	
}

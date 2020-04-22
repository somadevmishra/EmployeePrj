package com.sbl.emp.security;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import io.jsonwebtoken.Claims;

public class JWTFilter extends BasicAuthenticationFilter {

	@Autowired
	JWTUtils jwtUtils;
	
	public JWTFilter(AuthenticationManager authenticationManager) {
		super(authenticationManager);
		// TODO Auto-generated constructor stub
	}

	public JWTFilter(AuthenticationManager authenticationManager, AuthenticationEntryPoint authenticationEntryPoint) {
		super(authenticationManager, authenticationEntryPoint);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		
		String token = request.getHeader("token");
		
		if(jwtUtils.validateUser(token)) {
			Claims claims = jwtUtils.getClaims(token);
			String username = (String) claims.get("username");
			String password = (String) claims.get("password");
			Set<GrantedAuthority> authorities = new HashSet<>();
			List<String> roles = (List<String>)claims.get("roles", List.class);
			roles.forEach(e -> authorities.add(new SimpleGrantedAuthority(e)));
			
			UsernamePasswordAuthenticationToken upat = new UsernamePasswordAuthenticationToken(username, password, authorities);
			SecurityContextHolder.getContext().setAuthentication(upat);
		}
		chain.doFilter(request, response);
		
	}

}

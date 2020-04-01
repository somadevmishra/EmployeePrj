package com.sbl.emp.config;

import org.springframework.beans.factory.annotation.Autowired;
/**
 * this class overrides the default security configuration.
 * The default configuration make sure that all the url are fetched throgh authenticated users
 * So it provides a log in page. The default login user is "user" and passwords is printed in the log
 */
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	UserDetailsService userDetailsService;

	public SecurityConfig() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * example of authentication
	 * Inmemory Authentication
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		/*
		 * this portion is to describe in memory 
		 * auth.inMemoryAuthentication() .withUser("userabc@def.com")
		 * .password("{noop}helloworld") .roles("USER") .and() .withUser("helloworld")
		 * .password("{noop}hworld") .roles("ADMIN") ;
		 */
		
		auth.userDetailsService(userDetailsService)
			.passwordEncoder(NoOpPasswordEncoder.getInstance());
	}
	
	/**
	 * Example of Authorization
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		http.authorizeRequests()
			.antMatchers("/allemployees")
			.hasAuthority("USER")
			.antMatchers("/getuser/*")
			.hasAuthority("ADMIN")
			.and()
			.formLogin()
			;
	}
	
	/*
	 * @Bean public PasswordEncoder passwordEncoder() { return
	 * NoOpPasswordEncoder.getInstance(); }
	 */
}

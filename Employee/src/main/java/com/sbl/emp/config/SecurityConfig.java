package com.sbl.emp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
/**
 * this class overrides the default security configuration.
 * The default configuration make sure that all the url are fetched throgh authenticated users
 * So it provides a log in page. The default login user is "user" and passwords is printed in the log
 */
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.sbl.emp.security.AuthanticationFilter;
import com.sbl.emp.security.JWTFilter;
import com.sbl.emp.util.ApplicationConstants;

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
			.passwordEncoder(new BCryptPasswordEncoder(ApplicationConstants.PASSWORDENCODERSTRENGTH));
			//.passwordEncoder(NoOpPasswordEncoder.getInstance());
	}
	
	/**
	 * Example of Authorization
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		http.authorizeRequests()
			.antMatchers("/v2/api-docs", 
				"/configuration/ui",
				"/swagger-resources/**", 
				"/configuration/**", 
				"/swagger-ui/**", 
				"/webjars/**")
			.permitAll()
			.anyRequest()
			.authenticated()
			.antMatchers("/login")
			.permitAll()
			.antMatchers("/allemployees")
			.hasAnyAuthority("USER", "ADMIN")
			.antMatchers("/getuser/*")
			.hasAuthority("ADMIN")
			.and()
			.formLogin()
			.and()
			.csrf()
			.disable()
			.addFilter(getAthenticationFilter())
			.addFilterAfter(getJwtFilter(), UsernamePasswordAuthenticationFilter.class)
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			;
	}
	
	/*
	 * @Bean public PasswordEncoder passwordEncoder() { return
	 * NoOpPasswordEncoder.getInstance(); }
	 */
	
	@Override
	public void configure(WebSecurity web) throws Exception {

		//web.ignoring().antMatchers("/swagger*/**");
		web.ignoring()
		.antMatchers("/v2/api-docs", 
				"/configuration/ui",
				"/swagger-resources/**", 
				"/configuration/**", 
				"/swagger-ui.html/**", 
				"/webjars/**")
		;
		
	}
	
	@Override
	protected AuthenticationManager authenticationManager() throws Exception {
		// TODO Auto-generated method stub
		return super.authenticationManager();
	}
	
	@Bean
	public AuthanticationFilter getAthenticationFilter() throws Exception {
		
		AuthanticationFilter authanticationFilter = new AuthanticationFilter();
		
		authanticationFilter.setAuthenticationManager(authenticationManager());
		
		return authanticationFilter;
	}
	
	
	@Bean
	public JWTFilter getJwtFilter() throws Exception {
		return new JWTFilter(authenticationManager()); 
	}
	
}

package com.sbl.emp.Employee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.sbl.emp.*"})
@EnableJpaRepositories(basePackages = "com.sbl.emp.repo")
@EntityScan(basePackages =  "com.sbl.emp.model")
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        
        SpringApplication.run(App.class, args);
    }
	
}

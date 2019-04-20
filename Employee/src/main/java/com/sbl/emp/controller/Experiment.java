package com.sbl.emp.controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
@RequestMapping("/experiment")
public class Experiment {

	public Experiment() {
		
	}

	@GetMapping(path ="/home" )
	public String home(){
		return "Home get mappinngs";
	}

}

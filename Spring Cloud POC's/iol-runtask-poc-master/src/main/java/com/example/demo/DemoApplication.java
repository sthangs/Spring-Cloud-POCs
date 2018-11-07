package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.task.configuration.EnableTask;

import lombok.extern.java.Log;

@Log
@EnableTask
@SpringBootApplication
public class DemoApplication implements ApplicationRunner{
	
	
	public static void main(String[] args) {
		 SpringApplication sprintApplication =
	                new SpringApplication(DemoApplication.class);
		 sprintApplication.run(args);
		
	}
	
	@Autowired
	TestService ts;
	
	@Override
	 public void run(ApplicationArguments applicationArguments) throws Exception {

	    	System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>Hello<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
	    	
	    	if(applicationArguments.containsOption("keyValue")) {
	    		ts.apptry();
	    		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>Im Running task<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
	    	}
	    	if(applicationArguments.getOptionValues("opVal") != null) {
	    		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>Im Running task opval opt2<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
	    	}
	    	
	 }
}

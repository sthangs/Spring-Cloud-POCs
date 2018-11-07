package com.example.demo;

import org.springframework.stereotype.Service;

@Service
public class TestService {
	
	public String apptry() {
		
		System.out.println(">>service class op<<<<<<<<<<<<<<<<<");
		return "service hit";
		
	}

}

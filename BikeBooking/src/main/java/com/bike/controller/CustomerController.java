package com.bike.controller;



import org.springframework.beans.factory.annotation.Autowired;
import com.bike.service.CustomerService;

public class CustomerController {
	
	public CustomerController() {
		System.out.println("In ctor of" + getClass().getName());
	}
	
	@Autowired
	private CustomerService customerService; 
	
	

	
}

package com.bike.controller;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.bike.dto.SignUpDTO;
import com.bike.service.CustomerService;

public class CustomerController {
	
	public CustomerController() {
		System.out.println("In ctor of" + getClass().getName());
	}
	
	@Autowired
	private CustomerService customerService; 
	
	

	
}

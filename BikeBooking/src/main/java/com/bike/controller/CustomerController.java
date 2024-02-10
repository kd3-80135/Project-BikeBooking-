package com.bike.controller;



import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.bike.service.CustomerService;

public class CustomerController {
	
	public CustomerController() {
		System.out.println("In ctor of" + getClass().getName());
	}
	
	@Autowired
	private CustomerService customerService; 
	
	@PostMapping("/addBikeToCart/{cartId}/{bikeId}")
	public ResponseEntity<?> addBikeToCart(@PathVariable @Valid long cartId, @PathVariable @Valid long bikeId ){
		System.out.println("In addBikeToCart method of " + getClass().getName());
		return customerService.addBikeToCartService(cartId, bikeId);
	}

	
}

package com.bike.controller;



import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

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
	
	@PostMapping("/addPartToCart/{cartId}/{partid}")
	public ResponseEntity<?> addpartToCart (@PathVariable @Valid long cartId, @PathVariable @Valid long partId){
		System.out.println("In addPartToCart method of " + getClass().getName());
		return customerService.addPartToCartService(cartId, partId);
	}
	
	@PostMapping("/removeBikeFromCart/{cartId}/{bikeId}")
	public ResponseEntity<?> removeBikeFromCart(@PathVariable @Valid long cartId, @PathVariable @Valid long bikeId ){
		System.out.println("In removeBikeFromCart method of " + getClass().getName());
		return customerService.removeBikeFromCartService(cartId, bikeId);
	}
	
	@PostMapping("/removeBikeFromCart/{cartId}/{partId}")
	public ResponseEntity<?> removePartFromCart(@PathVariable @Valid long cartId, @PathVariable @Valid long partId ){
		System.out.println("In removePartFromCart method of " + getClass().getName());
		return customerService.removePartFromCartService(cartId, partId);
	}
	
	@PutMapping ("/increaseBikeCount/{cartId}")
		public ResponseEntity<?> increaseBikeCount (@PathVariable @ Valid long cartId){
		System.out.println("In increaseBikeCount method of " + getClass().getName());
		return customerService.increaseBikeCountService(cartId);
	}
	
	@PutMapping ("/increasePartCount/{cartId}")
		public ResponseEntity<?> increasePartCount (@PathVariable @ Valid long cartId){
		System.out.println("In increasePartCount method of " + getClass().getName());
		return customerService.increasePartCountService(cartId);
	}
	
	@PutMapping ("/decreaseBikeCount/{cartId}")
		public ResponseEntity<?> decreaseBikeCount (@PathVariable @ Valid long cartId){
		System.out.println("In decreaseBikeCount method of " + getClass().getName());
		return customerService.decreaseBikeCountService(cartId);
	}
		
	@PutMapping ("/decreasePartCount/{cartId}")
		public ResponseEntity<?> decreasePartCount (@PathVariable @ Valid long cartId){
		System.out.println("In decreasePartCount method of " + getClass().getName());
		return customerService.decreasePartCountService(cartId);
	}

	
}

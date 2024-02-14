package com.bike.controller;



import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bike.service.CustomerService;

@RestController
@RequestMapping ("/customer")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Validated
public class CustomerController {
	
	public CustomerController() {
		System.out.println("In ctor of" + getClass().getName());
	}
	
	@Autowired
	private CustomerService customerService; 
	
	@PostMapping("/addBikeToCart/{Id}/{bikeId}")
	public ResponseEntity<?> addBikeToCart(@PathVariable @Valid long cartId, @PathVariable @Valid long bikeId ){
		System.out.println("In addBikeToCart method of " + getClass().getName());
		return customerService.addBikeToCartService(cartId, bikeId);
	}
	
	@PostMapping("/addPartToCart/{cartId}/{partid}")
	public ResponseEntity<?> addpartToCart (@PathVariable @Valid long cartId, @PathVariable @Valid long partId){
		System.out.println("In addPartToCart method of " + getClass().getName());
		return customerService.addPartToCartService(cartId, partId);
	}
	
	@PostMapping("/removeBikeFromCart/{cartId}")
	public ResponseEntity<?> removeBikeFromCart(@PathVariable @Valid long cartId){
		System.out.println("In removeBikeFromCart method of " + getClass().getName());
		return customerService.removeBikeFromCartService(cartId);
	}
	
	@PostMapping("/removePartFromCart/{cartId}")
	public ResponseEntity<?> removePartFromCart(@PathVariable @Valid long cartId){
		System.out.println("In removePartFromCart method of " + getClass().getName());
		return customerService.removePartFromCartService(cartId);
	}
	
	@PutMapping ("/increaseBikeCount/{cartId}/{partId}")
	public ResponseEntity<?> increaseBikeCount (@PathVariable @Valid long cartId, @PathVariable @Valid long partId){
		System.out.println("In increaseBikeCount method of " + getClass().getName());
		return customerService.increaseBikeCountService(cartId, partId);
	}
	
	@PutMapping ("/increasePartCount/{cartId}/{partId}")
	public ResponseEntity<?> increasePartCount (@PathVariable @Valid long cartId, @PathVariable @Valid long partId){
		System.out.println("In increasePartCount method of " + getClass().getName());
		return customerService.increasePartCountService(cartId, partId);
	}
	
	@PutMapping ("/decreaseBikeCount/{cartId}/{partId}")
	public ResponseEntity<?> decreaseBikeCount (@PathVariable @Valid long cartId , @PathVariable @Valid long partId){
		System.out.println("In decreaseBikeCount method of " + getClass().getName());
		return customerService.decreaseBikeCountService(cartId, partId);
	}
		
	@PutMapping ("/decreasePartCount/{cartId}/{partId}")
	public ResponseEntity<?> decreasePartCount (@PathVariable @Valid long cartId , @PathVariable @Valid long partId){
		System.out.println("In decreasePartCount method of " + getClass().getName());
		return customerService.decreasePartCountService(cartId, partId);
	}
	
	@GetMapping ("/bikeList/{id}")
	public ResponseEntity<?> bikeList (@PathVariable @Valid long id){
		System.out.println("In bikeList method of " + getClass().getName());
		return customerService.bikeListService(id);
	}
	
	@GetMapping ("/partList/{id}")
	public ResponseEntity<?> partList (@PathVariable @Valid long id){
		System.out.println("In partList method of " + getClass().getName());
		return customerService.partListService(id);
	}
	
	@GetMapping ("/bike/{id}")
	public ResponseEntity<?> getBike(@PathVariable @Valid Long id){
		System.out.println("In getBike method of " + getClass().getName());
		return customerService.getBikeService(id);
	}
	
	@GetMapping ("/part/{id}")
	public ResponseEntity<?> getPart(@PathVariable @Valid Long id){
		System.out.println("In getPart method of " + getClass().getName());
		return customerService.getPartService(id);
	}

	
}

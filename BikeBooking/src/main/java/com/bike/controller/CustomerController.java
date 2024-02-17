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
	public ResponseEntity<?> addBikeToCart(@PathVariable @Valid long Id, @PathVariable @Valid long bikeId ){
		System.out.println("In addBikeToCart method of " + getClass().getName());
		return customerService.addBikeToCartService(Id, bikeId);
	}
	
	@PostMapping("/addPartToCart/{Id}/{partId}")
	public ResponseEntity<?> addpartToCart (@PathVariable @Valid long Id, @PathVariable @Valid long partId){
		System.out.println("In addPartToCart method of " + getClass().getName());
		return customerService.addPartToCartService(Id, partId);
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
	
	@PutMapping ("/increaseBikeCount/{cartId}")
	public ResponseEntity<?> increaseBikeCount (@PathVariable @Valid long cartId){
		System.out.println("In increaseBikeCount method of " + getClass().getName());
		return customerService.increaseBikeCountService(cartId);
	}
	
	@PutMapping ("/increasePartCount/{cartId}")
	public ResponseEntity<?> increasePartCount (@PathVariable @Valid long cartId){
		System.out.println("In increasePartCount method of " + getClass().getName());
		return customerService.increasePartCountService(cartId);
	}
	
	@PutMapping ("/decreaseBikeCount/{cartId}")
	public ResponseEntity<?> decreaseBikeCount (@PathVariable @Valid long cartId){
		System.out.println("In decreaseBikeCount method of " + getClass().getName());
		return customerService.decreaseBikeCountService(cartId);
	}
		
	@PutMapping ("/decreasePartCount/{cartId}")
	public ResponseEntity<?> decreasePartCount (@PathVariable @Valid long cartId){
		System.out.println("In decreasePartCount method of " + getClass().getName());
		return customerService.decreasePartCountService(cartId);
	}
	
	@GetMapping ("/bikeList")
	public ResponseEntity<?> bikeList (){
		System.out.println("In bikeList method of " + getClass().getName());
		return customerService.bikeListService();
	}
	
	@GetMapping ("/partList")
	public ResponseEntity<?> partList (){
		System.out.println("In partList method of " + getClass().getName());
		return customerService.partListService();
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

	@GetMapping ("/cartBikeList/{userId}")
	public ResponseEntity<?> cartBikeList(@PathVariable @Valid Long userId){
		System.out.println("In cartBikeList method of " + getClass().getName());
		return customerService.cartBikeListService(userId);
	}
	
	@GetMapping ("/cartPartList/{userId}")
	public ResponseEntity<?> cartPartList (@PathVariable @Valid Long userId){
		System.out.println("In cartPartList method of " + getClass().getName());
		return customerService.cartPartListService(userId);
	}
	
	@PostMapping ("/order/{userId}")
	public ResponseEntity<?> order(@PathVariable @Valid Long userId){
		System.out.println("In Order method of " + getClass().getName());
		return customerService.order(userId);
	}
	
	
	
	
}

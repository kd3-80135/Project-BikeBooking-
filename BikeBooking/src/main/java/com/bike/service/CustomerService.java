package com.bike.service;


import org.springframework.http.ResponseEntity;

public interface CustomerService {

	ResponseEntity<?> addBikeToCartService(long cartId, long bikeId);
	

}

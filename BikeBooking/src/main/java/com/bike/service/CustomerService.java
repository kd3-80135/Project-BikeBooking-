package com.bike.service;




import javax.validation.Valid;

import org.springframework.http.ResponseEntity;

public interface CustomerService {

	ResponseEntity<?> addBikeToCartService(long cartId, long bikeId);

	ResponseEntity<?> addPartToCartService(long cartId, long partId);

	ResponseEntity<?> removeBikeFromCartService(long cartId, long bikeId);

	ResponseEntity<?> removePartFromCartService(long cartId, long partId);

	ResponseEntity<?> increaseBikeCountService(long cartId);

	ResponseEntity<?> increasePartCountService(long cartId);

	ResponseEntity<?> decreaseBikeCountService(long cartId);

	ResponseEntity<?> decreasePartCountService(long cartId);

	ResponseEntity<?> bikeListService();

	ResponseEntity<?> partListService();

	ResponseEntity<?> getBikeService(Long id);

	ResponseEntity<?> getPartService(Long id);
	

}

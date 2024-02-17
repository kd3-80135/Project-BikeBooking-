package com.bike.service;

import org.springframework.http.ResponseEntity;

public interface CustomerService {

	ResponseEntity<?> addBikeToCartService(long cartId, long bikeId);

	ResponseEntity<?> addPartToCartService(long cartId, long partId);

	ResponseEntity<?> removeBikeFromCartService(long cartId);

	ResponseEntity<?> removePartFromCartService(long cartId);

	ResponseEntity<?> increaseBikeCountService(long cartId);

	ResponseEntity<?> increasePartCountService(long cartId);

	ResponseEntity<?> decreaseBikeCountService(long cartId);

	ResponseEntity<?> decreasePartCountService(long cartId);

	ResponseEntity<?> bikeListService();

	ResponseEntity<?> partListService();

	ResponseEntity<?> getBikeService(Long id);

	ResponseEntity<?> getPartService(Long id);

	ResponseEntity<?> cartBikeListService(Long userid);

	ResponseEntity<?> cartPartListService(Long userid);

	ResponseEntity<?> order(Long userId);
	

}

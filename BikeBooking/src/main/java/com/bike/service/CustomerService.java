package com.bike.service;




import javax.validation.Valid;

import org.springframework.http.ResponseEntity;

public interface CustomerService {

	ResponseEntity<?> addBikeToCartService(long cartId, long bikeId);

	ResponseEntity<?> addPartToCartService(long cartId, long partId);

	ResponseEntity<?> removeBikeFromCartService(long cartId);

	ResponseEntity<?> removePartFromCartService(long cartId);

	ResponseEntity<?> increaseBikeCountService(long cartId, long partId);

	ResponseEntity<?> increasePartCountService(long cartId, long partId);

	ResponseEntity<?> decreaseBikeCountService(long cartId, long partId);

	ResponseEntity<?> decreasePartCountService(long cartId, long partId);

	ResponseEntity<?> bikeListService(long id);

	ResponseEntity<?> partListService(long id);

	ResponseEntity<?> getBikeService(Long id);

	ResponseEntity<?> getPartService(Long id);
	

}

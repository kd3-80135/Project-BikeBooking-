package com.bike.service;


import javax.validation.Valid;

import org.springframework.http.ResponseEntity;

public interface AdminService {

	ResponseEntity<?> getUserListService();

	ResponseEntity<?> deleteUserService(long id);

	ResponseEntity<?> blockUserService(long id);

	ResponseEntity<?> unBlockUserService(long id);

	ResponseEntity<?> getBikeListService();

	ResponseEntity<?> getPartListService();

	ResponseEntity<?> approveBikeService(long id);

	ResponseEntity<?> approvePartService(long id);

	ResponseEntity<?> disproveBikeService(long id);

	ResponseEntity<?> disprovePartService(long id);

	ResponseEntity<?> deleteBikeService(long id);

	ResponseEntity<?> deletePartService(long id);

	ResponseEntity<?> orderListService();
	
	
	

}

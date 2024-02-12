package com.bike.service;


import org.springframework.http.ResponseEntity;

public interface AdminService {

	ResponseEntity<?> getUserListService();

	ResponseEntity<?> deleteUserService(long id);

	ResponseEntity<?> blockUserService(long id);

	ResponseEntity<?> unBlockUserService(String email);

	ResponseEntity<?> getBikeListService();

	ResponseEntity<?> getPartListService();
	
	
	

}

package com.bike.service;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;

import com.bike.dto.AddBikeDTO;
import com.bike.dto.AddPartDTO;

public interface DealerService {

	ResponseEntity<?> getBikeListService(long id);
	
	ResponseEntity<?> addBikeService(long id, AddBikeDTO addBike);

	ResponseEntity<?> getPartListService(long id);

	ResponseEntity<?> addPartService(long id, AddPartDTO partdto);

	ResponseEntity<?> deleteBikeService(long bikeId);

	ResponseEntity<?> deletePartService(long partId);
	

}

package com.bike.service;


import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.bike.dto.AddBikeDTO;
import com.bike.dto.AddPartDTO;

public interface DealerService {

	ResponseEntity<?> getBikeListService(long id);
	
	ResponseEntity<?> addBikeService(long id, AddBikeDTO addBike);

	ResponseEntity<?> getPartListService(long id);

	ResponseEntity<?> addPartService(long id, AddPartDTO partdto);

	ResponseEntity<?> deleteBikeService(long bikeId);

	ResponseEntity<?> deletePartService(long partId);

	ResponseEntity<?> editBikeService(long bikeId);

	ResponseEntity<?> editPartService(long partId);

	ResponseEntity<?> updateBikeService(long bikeId, AddBikeDTO bikeDTO);

	ResponseEntity<?> updatePartService(long partId, AddPartDTO partDTO);
	
	ResponseEntity<?> uploadBikeImageToFolderPathToDBService(Long bikeId, MultipartFile image) throws IOException;
	
	ResponseEntity<?> uploadPartImageToFolderPathToDBService(Long bikeId, MultipartFile image) throws IOException;
	

}

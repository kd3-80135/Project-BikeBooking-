package com.bike.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.bike.dao.PartDao;
import com.bike.dao.TwoWheelerDao;
import com.bike.dao.UserDao;
import com.bike.dto.AddBikeDTO;
import com.bike.dto.AddPartDTO;
import com.bike.dto.ResponseDealerBikeDTO;
import com.bike.dto.ResponseDealerPartDTO;
import com.bike.entities.Parts;
import com.bike.entities.TwoWheelers;
import com.bike.entities.User;
import com.bike.exceptions.ResourceNotFoundException;
import com.bike.exceptions.UserAlreadyExistsException;

@Service
@Transactional
public class DealerServiceImpl implements DealerService {
	
	public DealerServiceImpl() {
		System.out.println("In ctor of " + getClass().getName());
	}
	
	@Value("${folder.location}")
	private String folderLocation;
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private TwoWheelerDao twoWheelerDao;
	
	@Autowired
	private PartDao partDao;

	@Override
	public ResponseEntity<?> getBikeListService(long id) {
		// TODO Auto-generated method stub
		List<TwoWheelers> twoWheelerList = userDao.getTwoWheelers(id);
		if(twoWheelerList.isEmpty()) {
			throw new ResourceNotFoundException("No bikes found");
		}
		else {
			List<ResponseDealerBikeDTO> responseDealerBikeDTO = twoWheelerList.stream()
										.map(u-> new ResponseDealerBikeDTO(u.getId(),u.getName(), u.getQuantity(),
												u.getBikeType(), u.getBikeBrands(),u.getColour(),u.isApproveStatus()))
										.collect(Collectors.toList());
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(responseDealerBikeDTO);
												
		}
	}

	@Override
	public ResponseEntity<?> addBikeService(long id, AddBikeDTO bikedto) {
		String bikeName = bikedto.getName();
		TwoWheelers twoWheeler = twoWheelerDao.findBikeName(bikeName);
		if(twoWheeler != null) {
			twoWheeler.setQuantity(twoWheeler.getQuantity()+ bikedto.getQuantity());
		String message = "Successful Incremented";
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(message);
		}
		else {
			User user = userDao.findById(id).get();
			TwoWheelers bike = mapper.map(bikedto, TwoWheelers.class);
			user.addBike(bike);
			TwoWheelers detachedBike = twoWheelerDao.save(bike);
			if(detachedBike!= null){	
				String message = "Record Successfully Added";
				return ResponseEntity.status(HttpStatus.ACCEPTED).body(detachedBike.getId());
			}
			else
			{
				throw new RuntimeException("Some SQL Error Occured");
			}
 		}
	}


	@Override
	public ResponseEntity<?> getPartListService(long id) {
		List<Parts> part = userDao.getParts(id);
		if(part.isEmpty()) {
			throw new ResourceNotFoundException("No parts found");
		}
		else {
			List<ResponseDealerPartDTO> responseDealerPartDTO = part.stream()
										.map(u-> new ResponseDealerPartDTO(u.getId(), u.getName(),
												u.getDescription(), u.getQuantity(), u.getPrice(),u.isApproveStatus()))
										.collect(Collectors.toList());
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(responseDealerPartDTO);
												
		}
	}

	@Override
	public ResponseEntity<?> addPartService(long id, AddPartDTO partdto) {
		String partName = partdto.getName();
		Parts parts = partDao.findPartName(partName);
		if(parts != null) {
			parts.setQuantity(parts.getQuantity()+ partdto.getQuantity());
		String message = "Successful Incremented";
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(message);
		}
		else {
			User user = userDao.findById(id).get();
			Parts part = mapper.map(partdto, Parts.class);
			user.addBikePart(part);
			Parts detachedPart = partDao.save(part);
			if(detachedPart!= null){	
				String message = "Record Successfully Added";
				return ResponseEntity.status(HttpStatus.ACCEPTED).body(detachedPart.getId());
			}
			else
			{
				throw new RuntimeException("Some Sql Error Occured");
			}
 		}
	}

	@Override
	public ResponseEntity<?> deleteBikeService(long bikeId) {
		TwoWheelers bike = twoWheelerDao.findById(bikeId).get();
		if (bike != null) {
			bike.setDeleteStatus(true);
			String message = "Bike deleted successfully.";
			return ResponseEntity.status(HttpStatus.OK).body(message);
		}
		else {
			throw new ResourceNotFoundException("Invalid credentials!! No such bike exists.");
		}
	}

	@Override
	public ResponseEntity<?> deletePartService(long partId) {
		Parts part = partDao.findById(partId).get();
		if (part != null) {
			part.setDeleteStatus(true);
			String message = "Part deleted successfully.";
			return ResponseEntity.status(HttpStatus.OK).body(message);
		}
		else {
			throw new ResourceNotFoundException("Invalid credentials!! No such part exists.");
		}
	}

	@Override
	public ResponseEntity<?> editBikeService(long bikeId) {
		TwoWheelers bike = twoWheelerDao.findById(bikeId).get();
		if (bike != null) {
			return ResponseEntity.status(HttpStatus.OK).body(mapper.map(bike, AddBikeDTO.class));
		}
		else {
			throw new ResourceNotFoundException("Invalid credentials!! No such bike found.");
		}
	}

	@Override
	public ResponseEntity<?> editPartService(long partId) {
		Parts part = partDao.findById(partId).get();
		if (part != null) {
			return ResponseEntity.status(HttpStatus.OK).body(mapper.map(part, AddPartDTO.class));
		}
		else {
			throw new ResourceNotFoundException("Invalid credentials!! No such part found.");
		}
	}

	@Override
	public ResponseEntity<?> updateBikeService(long bikeId, AddBikeDTO bikeDTO) {
		TwoWheelers bike = twoWheelerDao.findById(bikeId).get();
		bike.setName(bikeDTO.getName());
		bike.setPrice(bikeDTO.getPrice());
		bike.setQuantity(bike.getQuantity());
		bike.setBikeType(bikeDTO.getBikeType());
		bike.setBikeBrands(bikeDTO.getBikeBrands());
		bike.setDescription(bikeDTO.getDescription());
		bike.setColour(bike.getColour());
		bike.setApproveStatus(bikeDTO.isApproveStatus());
		TwoWheelers detachedBike = twoWheelerDao.save(bike);
		if (detachedBike != null) {
			return ResponseEntity.status(HttpStatus.OK).body(mapper.map(detachedBike, AddBikeDTO.class));
		}
		else {
			throw new UserAlreadyExistsException("Some sql exception occured while adding bike.");
		}
	}

	@Override
	public ResponseEntity<?> updatePartService(long partId, AddPartDTO partDTO) {
		Parts part = partDao.findById(partId).get();
		part.setName(partDTO.getName());
		part.setPrice(partDTO.getPrice());
		part.setDescription(part.getDescription());
		part.setQuantity(part.getQuantity());
		part.setApproveStatus(part.isApproveStatus());
		Parts detachedPart = partDao.save(part);
		if (detachedPart != null) {
			return ResponseEntity.status(HttpStatus.OK).body(mapper.map(detachedPart, AddPartDTO.class));
		}
		else {
			throw new UserAlreadyExistsException("Some sql exception occured while adding part.");
		}
	}
	
	@Override
	public ResponseEntity<?> uploadBikeImageToFolderPathToDBService(Long bikeId, MultipartFile image) throws IOException{
		TwoWheelers bike = twoWheelerDao.findById(bikeId).orElseThrow(() -> new ResourceNotFoundException("Invalid bike ID"));
		File folder = new File(folderLocation);
		if (folder.exists()) {
			System.out.println("folder exists already.");
		} else {
			folder.mkdir();
			System.out.println("created a folder.");
		}
		String path = folderLocation.concat(bike.getName());
		FileUtils.writeByteArrayToFile(new File(path), image.getBytes());
		bike.setImagePath(path);
		String message = "Image and image path saved successfully.";
		return ResponseEntity.status(HttpStatus.OK).body(message);
	}
	
	public ResponseEntity<?> uploadPartImageToFolderPathToDBService(Long partId, MultipartFile image) throws IOException{
		Parts part = partDao.findById(partId).orElseThrow(() -> new ResourceNotFoundException("Invalid part ID"));
		File folder = new File(folderLocation);
		if (folder.exists()) {
			System.out.println("folder exists already.");
		} else {
			folder.mkdir();
			System.out.println("created a folder.");
		}
		String path = folderLocation.concat(part.getName());
		FileUtils.writeByteArrayToFile(new File(path), image.getBytes());
		part.setImagePath(path);
		String message = "Image and image path saved successfully.";
		return ResponseEntity.status(HttpStatus.OK).body(message);
	}
	
	

}

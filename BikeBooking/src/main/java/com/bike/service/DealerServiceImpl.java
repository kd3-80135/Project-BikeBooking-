package com.bike.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

@Service
@Transactional
public class DealerServiceImpl implements DealerService {
	
	public DealerServiceImpl() {
		System.out.println("In ctor of " + getClass().getName());
	}
	
	
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
				return ResponseEntity.status(HttpStatus.ACCEPTED).body(message);
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
				return ResponseEntity.status(HttpStatus.ACCEPTED).body(message);
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

}

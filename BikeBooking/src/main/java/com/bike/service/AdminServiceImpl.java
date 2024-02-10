package com.bike.service;

import java.util.List;
import java.util.stream.Collectors;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bike.dao.AdminDao;
import com.bike.dao.PartDao;
import com.bike.dao.TwoWheelerDao;
import com.bike.dao.UserDao;
import com.bike.dto.ResponseAdminBikeDTO;
import com.bike.dto.ResponseAdminPartDTO;
import com.bike.dto.ResponseUserDTO;
import com.bike.entities.Parts;
import com.bike.entities.TwoWheelers;
import com.bike.entities.User;
import com.bike.exceptions.ResourceNotFoundException;

@org.springframework.transaction.annotation.Transactional
@Service
public class AdminServiceImpl implements AdminService{

	public AdminServiceImpl() {
		System.out.println("In ctor of " + getClass().getName());
	}
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private AdminDao adminDao;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private TwoWheelerDao twoWheelerDao;
	
	@Autowired
	private PartDao partDao;
	
	@Override
	public ResponseEntity<?> getUserListService() {
		List<User> userList = adminDao.getAllUsers();
		if (userList.isEmpty()) {
			throw new ResourceNotFoundException("No Users Exist in DB. Check user table.");
		}
		else {
			List<ResponseUserDTO> userDTOList = userList.stream()
								.map(u -> new ResponseUserDTO(u.getId(), u.getFirstName(), u.getLastName(), u.getEmail(), u.getPassword(), u.getMobile(), u.getRole(),u.isBlockStatus(),u.isDeleteStatus()))
								.collect(Collectors.toList());
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(userDTOList);
		}
	}

	@Override
	public ResponseEntity<?> deleteUserService(long id) {
		User user = userDao.findById(id).get();
		if (user != null) {
			user.setDeleteStatus(true);
			String message = "User deleted successfully";
			return ResponseEntity.status(HttpStatus.OK).body(message);
		}
		else {
			throw new ResourceNotFoundException("Invalid Credentials. No such user exists.");
		}
	}

	@Override
	public ResponseEntity<?> blockUserService(long id) {
		User user = userDao.findById(id).get();
		if (user != null) {
			user.setBlockStatus(true);
			String message = "User blocked successfully";
			return ResponseEntity.status(HttpStatus.OK).body(message);
		}
		else {
			throw new ResourceNotFoundException("Invalid Credentials. No such user exists.");
		}
	}

	@Override
	public ResponseEntity<?> unBlockUserService(long id) {
		User user = userDao.findById(id).get();
		if (user != null) {
			if (user.isDeleteStatus()) {
				String message = "User has been deleted.";
				return ResponseEntity.status(HttpStatus.OK).body(message);
			}
			else if (user.isBlockStatus()) {
				user.setBlockStatus(false);
				String message = "User has been UnBlocked successfully!!";
				return ResponseEntity.status(HttpStatus.OK).body(message);
			}
			else {
				String message = "User is already active.";
				return ResponseEntity.status(HttpStatus.OK).body(message);
			}
		}
		else {
			throw new ResourceNotFoundException("Invalid Credentials. No such user exists.");
		}
	}

	@Override
	public ResponseEntity<?> getBikeListService() {
		List<TwoWheelers> bikeList = twoWheelerDao.getAllBikes();
		if (bikeList.isEmpty()) {
			String message = "No two-wheelers present at the moment";
			return ResponseEntity.status(HttpStatus.OK).body(message);
		}
		else {
			List<ResponseAdminBikeDTO> bikeListDTO = bikeList.stream()
							.map(l -> new ResponseAdminBikeDTO(l.getId(), l.getName(), l.getQuantity(), l.getBikeType(), l.getBikeBrands(), l.getColour(), l.isApproveStatus(), l.isDeleteStatus()))
							.collect(Collectors.toList());
			return ResponseEntity.status(HttpStatus.OK).body(bikeListDTO);
		}
	}

	@Override
	public ResponseEntity<?> getPartListService() {
		List<Parts> partList = partDao.getAllParts();
		if (partList.isEmpty()) {
			String message = "No parts present at the moment";
			return ResponseEntity.status(HttpStatus.OK).body(message);
		}
		else {
			List<ResponseAdminPartDTO> partListDTO = partList.stream()
							.map(l -> new ResponseAdminPartDTO(l.getId(), l.getName(), l.getQuantity(), l.isApproveStatus(), l.isDeleteStatus()))
							.collect(Collectors.toList());
			return ResponseEntity.status(HttpStatus.OK).body(partListDTO);
		}
	}

	@Override
	public ResponseEntity<?> approveBikeService(long id) {
		TwoWheelers bike = twoWheelerDao.findById(id).get();
		if (bike != null) {
			bike.setApproveStatus(true);
			String message = "The bike " + bike.getName() + " has been approved for the customers.";
			return ResponseEntity.status(HttpStatus.OK).body(message);
		}
		else {
			throw new ResourceNotFoundException("Invalid credentials. No such bike exists.");
		}
	}

	
	@Override
	public ResponseEntity<?> approvePartService(long id) {
		Parts part = partDao.findById(id).get();
		if (part != null) {
			part.setApproveStatus(true);
			String message = "The part " + part.getName() + " has been approved for the customers.";
			return ResponseEntity.status(HttpStatus.OK).body(message);
		}
		else {
			throw new ResourceNotFoundException("Invalid credentials. No such bike exists.");
		}
	}

	
	@Override
	public ResponseEntity<?> disproveBikeService(long id) {
		TwoWheelers bike = twoWheelerDao.findById(id).get();
		if (bike != null) {
			bike.setApproveStatus(false);
			String message = "The bike " + bike.getName() + " will not be visible to the customers anymore.";
			return ResponseEntity.status(HttpStatus.OK).body(message);
		}
		else {
			throw new ResourceNotFoundException("Invalid credentials. No such bike exists.");
		}	
	}
	

	@Override
	public ResponseEntity<?> disprovePartService(long id) {
		Parts part = partDao.findById(id).get();
		if (part != null) {
			part.setApproveStatus(false);
			String message = "The part " + part.getName() + " will not be available for the customers anymore.";
			return ResponseEntity.status(HttpStatus.OK).body(message);
		}
		else {
			throw new ResourceNotFoundException("Invalid credentials. No such part exists.");
		}
	}

	@Override
	public ResponseEntity<?> deleteBikeService(long id) {
		TwoWheelers bike = twoWheelerDao.findById(id).get();
		if (bike != null) {
			bike.setDeleteStatus(true);
			String message = "Bike " + bike.getName() + " deleted successfully";
			return ResponseEntity.status(HttpStatus.OK).body(message);
		}
		else {
			throw new ResourceNotFoundException("Invalid Credentials. No such bike exists.");
		}
	}

	@Override
	public ResponseEntity<?> deletePartService(long id) {
		Parts part = partDao.findById(id).get();
		if (part != null) {
			part.setDeleteStatus(true);
			String message = "Part " + part.getName() + " deleted successfully";
			return ResponseEntity.status(HttpStatus.OK).body(message);
		}
		else {
			throw new ResourceNotFoundException("Invalid Credentials. No such part exists.");
		}
	}
	
	
	
	
	
	
	
	
	

	
	
	
}

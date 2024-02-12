package com.bike.service;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.validation.Valid;

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
								.map(u -> new ResponseUserDTO(u.getId(), u.getFirstName(), u.getLastName(), u.getEmail(), u.getPassword(), u.getMobile(), u.getRole()))
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
	public ResponseEntity<?> unBlockUserService(String email) {
		User user = userDao.findByEmail(email);
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
		List<TwoWheelers> bikeList = twoWheelerDao.findAll();
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
		List<Parts> partList = partDao.findAll();
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
	
	
	
	
	
	
	
	
	

	
	
	
}

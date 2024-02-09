package com.bike.service;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bike.dao.AdminDao;
import com.bike.dao.UserDao;
import com.bike.dto.ResponseUserDTO;
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
		
		return null;
	}

	
	
	
}

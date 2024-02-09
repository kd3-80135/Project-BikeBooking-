package com.bike.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bike.dao.AdminDao;
import com.bike.dao.UserDao;

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
		
		
		
		return null;
	}

	
	
	
}

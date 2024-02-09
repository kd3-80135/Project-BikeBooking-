package com.bike.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bike.dao.CustomerDao;

@org.springframework.transaction.annotation.Transactional
@Service
public class CustomerServiceImpl implements CustomerService {
	
	public CustomerServiceImpl() {
		System.out.println("In ctor of " + getClass().getName());
	}
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private CustomerDao customerDao;
	
	

}

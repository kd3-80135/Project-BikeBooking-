package com.bike.service;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bike.dao.CartDao;
import com.bike.dao.TwoWheelerDao;
import com.bike.dao.UserDao;
import com.bike.entities.Cart;
import com.bike.entities.TwoWheelers;
import com.bike.exceptions.ResourceNotFoundException;

@org.springframework.transaction.annotation.Transactional
@Service
public class CustomerServiceImpl implements CustomerService {
	
	public CustomerServiceImpl() {
		System.out.println("In ctor of " + getClass().getName());
	}
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private CartDao cartDao;
	
	@Autowired
	private TwoWheelerDao twoWheelerDao;

	@Override
	public ResponseEntity<?> addBikeToCartService(long cartId, long bikeId) {
		Cart cart = cartDao.findById(cartId).get();
		TwoWheelers bike = twoWheelerDao.findById(bikeId).get();
		if (cart != null && bike!= null) {
			bike.addCart(cart);
			String message = "Product added to cart!!";
			return ResponseEntity.status(HttpStatus.OK).body(message);
		}
		else {
			throw new ResourceNotFoundException("Either user(cart) or the bike is not available.");
		}
	}
	
	
	
	
	
	
	
	

}

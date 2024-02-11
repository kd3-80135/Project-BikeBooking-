package com.bike.service;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bike.dao.CartDao;
import com.bike.dao.PartDao;
import com.bike.dao.TwoWheelerDao;
import com.bike.entities.Cart;
import com.bike.entities.Parts;
import com.bike.entities.TwoWheelers;
import com.bike.exceptions.ResourceNotFoundException;

@Transactional
@Service
public class CustomerServiceImpl implements CustomerService {
	
	public CustomerServiceImpl() {
		System.out.println("In ctor of " + getClass().getName());
	}
	
	@Autowired
	private CartDao cartDao;
	
	@Autowired
	private TwoWheelerDao twoWheelerDao;
	
	@Autowired
	private PartDao partDao;

	@Override
	public ResponseEntity<?> addBikeToCartService(long cartId, long bikeId) {
		Cart cart = cartDao.findById(cartId).get();
		TwoWheelers bike = twoWheelerDao.findById(bikeId).get();
		if (cart != null && bike!= null) {
			bike.addCart(cart);
			String message = "Bike added to cart!!";
			return ResponseEntity.status(HttpStatus.OK).body(message);
		}
		else {
			throw new ResourceNotFoundException("Either user(cart) or the bike is not available.");
		}
	}

	@Override
	public ResponseEntity<?> addPartToCartService(long cartId, long partId) {
		Cart cart = cartDao.findById(cartId).get();
		Parts part = partDao.findById(cartId).get();
		if (cart != null && part!= null) {
			part.addCart(cart);
			String message = "Part added to cart!!";
			return ResponseEntity.status(HttpStatus.OK).body(message);
		}
		else {
			throw new ResourceNotFoundException("Either user(cart) or the part is not available.");
		}
	}

	@Override
	public ResponseEntity<?> removeBikeFromCartService(long cartId, long bikeId) {
		Cart cart = cartDao.findById(cartId).get();
		TwoWheelers bike = twoWheelerDao.findById(bikeId).get();
		if (cart != null && bike!= null) {
			bike.removeCart(cart);
			String message = "Bike removed from cart!!";
			return ResponseEntity.status(HttpStatus.OK).body(message);
		}
		else {
			throw new ResourceNotFoundException("Either user(cart) or the bike is not available.");
		}
	}

	@Override
	public ResponseEntity<?> removePartFromCartService(long cartId, long partId) {
		Cart cart = cartDao.findById(cartId).get();
		Parts part = partDao.findById(cartId).get();
		if (cart != null && part!= null) {
			part.removeCart(cart);
			String message = "Part added to cart!!";
			return ResponseEntity.status(HttpStatus.OK).body(message);
		}
		else {
			throw new ResourceNotFoundException("Either user(cart) or the part is not available.");
		}
	}

	@Override
	public ResponseEntity<?> increaseBikeCountService(long cartId) {
		Cart cart = cartDao.findById(cartId).get();
		if (cart != null) {
			if (cart.getBikeQuantity() < 3) {
				cart.setBikeQuantity(cart.getBikeQuantity() + 1);
				String message = "Two Wheelers incremented in cart!!";
				return ResponseEntity.status(HttpStatus.OK).body(message);
			}
			else {
				String message = "Max amount of Two Wheelers selected!!";
				return ResponseEntity.status(HttpStatus.OK).body(message);
			}
		}
		else {
			throw new ResourceNotFoundException("User(cart) is not available.");
		}
	}

	@Override
	public ResponseEntity<?> increasePartCountService(long cartId) {
		Cart cart = cartDao.findById(cartId).get();
		if (cart != null) {
			if (cart.getPartQuantity() < 5) {
				cart.setPartQuantity(cart.getPartQuantity() + 1);
				String message = "Part incremented in cart!!";
				return ResponseEntity.status(HttpStatus.OK).body(message);
			}
			else {
				String message = "Max amount of Parts selected!!";
				return ResponseEntity.status(HttpStatus.OK).body(message);
			}
		}
		else {
			throw new ResourceNotFoundException("User(cart) is not available.");
		}
	}

	@Override
	public ResponseEntity<?> decreaseBikeCountService(long cartId) {
		Cart cart = cartDao.findById(cartId).get();
		if (cart != null) {
			if (cart.getBikeQuantity() > 0) {
				cart.setBikeQuantity(cart.getBikeQuantity() - 1);
				String message = "Two Wheelers decremented in cart!!";
				return ResponseEntity.status(HttpStatus.OK).body(message);
			}
			else {
				String message = "Two Wheelers already at minimum quantity!!";
				return ResponseEntity.status(HttpStatus.OK).body(message);
			}
		}
		else {
			throw new ResourceNotFoundException("User(cart) is not available.");
		}
	}

	@Override
	public ResponseEntity<?> decreasePartCountService(long cartId) {
		Cart cart = cartDao.findById(cartId).get();
		if (cart != null) {
			if (cart.getPartQuantity() > 0) {
				cart.setPartQuantity(cart.getPartQuantity() + 1);
				String message = "Part decremented in cart!!";
				return ResponseEntity.status(HttpStatus.OK).body(message);
			}
			else {
				String message = " Parts selected are already at minimum amount!!";
				return ResponseEntity.status(HttpStatus.OK).body(message);
			}
		}
		else {
			throw new ResourceNotFoundException("User(cart) is not available.");
		}
	}
	
	
	
	
	
	
	
	

}

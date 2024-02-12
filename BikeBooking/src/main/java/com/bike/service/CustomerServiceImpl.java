package com.bike.service;

import org.springframework.transaction.annotation.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bike.dao.CartDao;
import com.bike.dao.PartDao;
import com.bike.dao.TwoWheelerDao;
import com.bike.dto.AddBikeDTO;
import com.bike.dto.AddPartDTO;
import com.bike.dto.BikeDTO;
import com.bike.dto.PartDTO;
import com.bike.entities.Cart;
import com.bike.entities.Parts;
import com.bike.entities.TwoWheelers;
import com.bike.exceptions.ResourceNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

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
	
	@Autowired
	private ModelMapper mapper;

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

	@Override
	public ResponseEntity<?> bikeListService() {
		List<TwoWheelers> bikeList = twoWheelerDao.findBikesAllForUsers();
		if (bikeList.isEmpty()) {
			throw new ResourceNotFoundException("No bikes available at the moment.");
		}
		else {
			List<AddBikeDTO> bikeDTOList = bikeList.stream()
							.map(b -> new AddBikeDTO(b.getName(), b.getPrice(), b.getQuantity(), b.getBikeType(), b.getBikeBrands(), b.getDescription(), b.getColour(), b.isApproveStatus())) 
							.collect(Collectors.toList());
			return ResponseEntity.status(HttpStatus.OK).body(bikeDTOList);
		}
	}

	@Override
	public ResponseEntity<?> partListService() {
		List<Parts> partList = partDao.findPartsAllForUsers();
		if (partList.isEmpty()) {
			throw new ResourceNotFoundException("No parts available at the moment.");
		}
		else {
			List<AddPartDTO> partDTOList = partList.stream()
							.map(p -> new AddPartDTO(p.getName(), p.getPrice(), p.getDescription(), p.getQuantity(), p.isApproveStatus()))
							.collect(Collectors.toList());
			return ResponseEntity.status(HttpStatus.OK).body(partDTOList);
		}
	}

	@Override
	public ResponseEntity<?> getBikeService(Long id) {
		TwoWheelers bike = twoWheelerDao.findById(id).get();
		if (bike != null) {
			return ResponseEntity.status(HttpStatus.OK).body(mapper.map(bike, BikeDTO.class));
		}
		else {
			throw new ResourceNotFoundException("Invalid id. No such bike exists.");
		}
	}

	@Override
	public ResponseEntity<?> getPartService(Long id) {
		Parts part = partDao.findById(id).get();
		if (part != null) {
			return ResponseEntity.status(HttpStatus.OK).body(mapper.map(part, PartDTO.class));
		}
		else {
			throw new ResourceNotFoundException("Invalid id. No such part exists.");
		}
	}
	
	
	
	
	
	
	
	
	
	

}

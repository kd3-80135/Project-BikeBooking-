package com.bike.service;

import org.springframework.transaction.annotation.Transactional;
import org.apache.catalina.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bike.dao.CartDao;
import com.bike.dao.PartDao;
import com.bike.dao.TwoWheelerDao;
import com.bike.dao.UserDao;
import com.bike.dto.AddBikeDTO;
import com.bike.dto.AddPartDTO;
import com.bike.dto.BikeDTO;
import com.bike.dto.CartBikeDTO;
import com.bike.dto.PartDTO;
import com.bike.entities.Cart;
import com.bike.entities.Parts;
import com.bike.entities.TwoWheelers;
import com.bike.entities.User;
import com.bike.exceptions.ResourceNotFoundException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
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

	@Autowired
	private UserDao userDao;
	
	@Override
	public ResponseEntity<?> addBikeToCartService(long Id, long bikeId) {
		User user = userDao.findById(Id).get();
		Cart cart = new Cart(user, 0, 0, LocalDate.now(), LocalDate.now(), null, null, null, false);
		TwoWheelers bike = twoWheelerDao.findById(bikeId).get();
		if (cart != null && bike!= null) {
			bike.addCart(cart);
			cart.setBikeQuantity(1);
			String message = "Bike added to cart!!";
			return ResponseEntity.status(HttpStatus.OK).body(message);
		}
		else {
			throw new ResourceNotFoundException("Either user(cart) or the bike is not available.");
		}
	}

	@Override
	public ResponseEntity<?> addPartToCartService(long cartId, long partId) {
		User user = userDao.findById(cartId).get();
		Cart cart = new Cart(user, 0, 0, LocalDate.now(), LocalDate.now(), null, null, null, false);
		Parts part = partDao.findById(partId).get();
		if (cart != null && part!= null) {
			part.addCart(cart);
			cart.setPartQuantity(1);
			String message = "Part added to cart!!";
			return ResponseEntity.status(HttpStatus.OK).body(message);
		}
		else {
			throw new ResourceNotFoundException("Either user(cart) or the part is not available.");
		}
	}

	@Override
	public ResponseEntity<?> removeBikeFromCartService(long cartId) {
		Cart cart = cartDao.findById(cartId).get();
		if (cart != null) {
			cartDao.delete(cart);
			String message = "Bike removed from cart!!";
			return ResponseEntity.status(HttpStatus.OK).body(message);
		}
		else {
			throw new ResourceNotFoundException("Either user(cart) or the bike is not available.");
		}
	}

	@Override
	public ResponseEntity<?> removePartFromCartService(long cartId) {
		Cart cart = cartDao.findById(cartId).get();
		if (cart != null) {
			cartDao.delete(cart);
			String message = "Part added to cart!!";
			return ResponseEntity.status(HttpStatus.OK).body(message);
		}
		else {
			throw new ResourceNotFoundException("Either user(cart) or the part is not available.");
		}
	}

	@Override
	public ResponseEntity<?> increaseBikeCountService(long cartId, long partId) {
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
	public ResponseEntity<?> increasePartCountService(long cartId, long partId) {
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
	public ResponseEntity<?> decreaseBikeCountService(long cartId, long partId) {
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
	public ResponseEntity<?> decreasePartCountService(long cartId, long partId) {
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
	public ResponseEntity<?> bikeListService(long id) {
		List<CartBikeDTO> bikeList = cartDao.bikeList(id);
		if (bikeList.isEmpty()) {
			throw new ResourceNotFoundException("No bikes available at the moment.");
		}
		else {
			List<CartBikeDTO> bikeDTOList = bikeList.stream()
							.map(b -> new CartBikeDTO(b.getBikeId(),b.getName(), b.getPrice(), b.getBikeQuantity(), b.getBikeType(), b.getBikeBrands(), b.getColour(), b.getImagePath())) 
							.collect(Collectors.toList());
			return ResponseEntity.status(HttpStatus.OK).body(bikeDTOList);
		}
	}

	@Override
	public ResponseEntity<?> partListService(long id) {
		List<Parts> partList = partDao.findAll();
		if (partList.isEmpty()) {
			throw new ResourceNotFoundException("No parts available at the moment.");
		}
		else {
			List<PartDTO> partDTOList = partList.stream()
							.map(p -> new PartDTO( p.getId(),p.getName(), p.getPrice(), p.getDescription(), p.getQuantity(), p.getImagePath()))
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

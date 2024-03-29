package com.bike.service;

import org.springframework.transaction.annotation.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bike.dao.CartDao;
import com.bike.dao.OrderDao;
import com.bike.dao.PartDao;
import com.bike.dao.TwoWheelerDao;
import com.bike.dao.UserDao;
import com.bike.dto.BikeDTO;
import com.bike.dto.CartBikeDTO;
import com.bike.dto.CartPartDTO;
import com.bike.dto.OrderDTO;
import com.bike.dto.PartDTO;
import com.bike.entities.Cart;
import com.bike.entities.Orders;
import com.bike.entities.Parts;
import com.bike.entities.TwoWheelers;
import com.bike.entities.User;
import com.bike.exceptions.ResourceNotFoundException;

import java.time.LocalDateTime;
import java.util.HashSet;
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

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private OrderDao orderDao;
	
	@Override
	public ResponseEntity<?> addBikeToCartService(long Id, long bikeId) {
		User user = userDao.findById(Id).get();
		Cart cart = new Cart(user, 0, 0, LocalDateTime.now(), LocalDateTime.now(), null, null, null, false);
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
		Cart cart = new Cart(user, 0, 0, LocalDateTime.now(), LocalDateTime.now(), null, null, null, false);
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
			String message = "Part removed to cart!!";
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
			List<BikeDTO> bikeDTOList = bikeList.stream()
							.map(b -> new BikeDTO(b.getName(),b.getId(), b.getPrice(), b.getQuantity(), b.getBikeType(), b.getBikeBrands(),b.getDescription(), b.getColour(), b.getImagePath())) 
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

	@Override
	public ResponseEntity<?> cartBikeListService(Long userid) {
		User user = userDao.findById(userid).get();
		List<CartBikeDTO> bikeList = cartDao.bikeList(user);
		if (bikeList.isEmpty()) {
			throw new ResourceNotFoundException("No Bikes present at the moment.");
		}
		else {
			List<CartBikeDTO> bikeDTOList = bikeList.stream()
					.map(b -> new CartBikeDTO(b.getCartId() ,b.getName(), b.getPrice(), b.getBikeQuantity(), b.getBikeType(), b.getBikeBrands(), b.getColour(), b.getImagePath()))
					.collect(Collectors.toList());
			return ResponseEntity.status(HttpStatus.OK).body(bikeDTOList);
		}
	}

	@Override
	public ResponseEntity<?> cartPartListService(Long userid) {
		User user = userDao.findById(userid).get();
		List<CartPartDTO> partList = cartDao.partList(user);
		if (partList.isEmpty()) {
			throw new ResourceNotFoundException("No Parts present at the moment.");
		}
		else {
			List<CartPartDTO> partDTOList = partList.stream()
					.map(b -> new CartPartDTO(b.getCartId(), b.getName(), b.getPrice(), b.getPartQuantity(), b.getImagePath()))
					.collect(Collectors.toList());
			return ResponseEntity.status(HttpStatus.OK).body(partDTOList);
		}
	}

	@Override
	public ResponseEntity<?> order(Long userId) {
		User user =  userDao.findById(userId).get();
		List<CartBikeDTO> bikeList = cartDao.bikeList(user);
		List<CartPartDTO> partList = cartDao.partList(user);
		Orders order = new Orders(user, new HashSet<TwoWheelers>(), new HashSet<Parts>(), false, null, null, null, false, null, null, null, false);
		if (partList.isEmpty() && bikeList.isEmpty()) {
			throw new ResourceNotFoundException("No bikes and parts available in cart");
		}
		else {
			if (bikeList.isEmpty()) {
				for (CartPartDTO cartPartDTO : partList) {
					Parts part = partDao.findPartName(cartPartDTO.getName());
					part.addOrders(order);
					part.setQuantity(part.getQuantity() - cartPartDTO.getPartQuantity());
					removePartFromCartService(cartPartDTO.getCartId());
				}
				order.setPlacedAt(LocalDateTime.now());
			}
			else if (partList.isEmpty()){
				for (CartBikeDTO cartBikeDTO : bikeList) {
					TwoWheelers bike = twoWheelerDao.findBikeName(cartBikeDTO.getName());
					bike.addOrders(order);
					bike.setQuantity(bike.getQuantity() - cartBikeDTO.getBikeQuantity());
					removeBikeFromCartService(cartBikeDTO.getCartId());
				}
				order.setPlacedAt(LocalDateTime.now());
			}
			else{
				for (CartPartDTO cartPartDTO : partList) {
					Parts part = partDao.findPartName(cartPartDTO.getName());
					part.addOrders(order);
					part.setQuantity(part.getQuantity() - cartPartDTO.getPartQuantity());
					removePartFromCartService(cartPartDTO.getCartId());
				}
				for (CartBikeDTO cartBikeDTO : bikeList) {
					TwoWheelers bike = twoWheelerDao.findBikeName(cartBikeDTO.getName());
					bike.addOrders(order);
					bike.setQuantity(bike.getQuantity() - cartBikeDTO.getBikeQuantity());
					removeBikeFromCartService(cartBikeDTO.getCartId());
				}
				order.setPlacedAt(LocalDateTime.now());
			}
			String message = "Order Placed Successfully.";
			return ResponseEntity.status(HttpStatus.OK).body(message);
		}			
	}

	@Override
	public ResponseEntity<?> myOrders(Long userId) {
		User user = userDao.findById(userId).get();
		List<OrderDTO> orderList = orderDao.findByThisCustomer(user);
		if (orderList.isEmpty()) {
			String message = "No orders present at the moment.";
			return ResponseEntity.status(HttpStatus.OK).body(message);
		}
		else {
			return ResponseEntity.status(HttpStatus.OK).body(orderList);
		}
	}
	
	
	
	
	
	
}

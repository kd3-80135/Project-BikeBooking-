package com.bike.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bike.dto.CartBikeDTO;
import com.bike.entities.Cart;
import com.bike.entities.Parts;
import com.bike.entities.TwoWheelers;


public interface CartDao extends JpaRepository<Cart, Long>{

//	@Query("select b.bikeId, b.name, b.price, c.bikeQuantity, b.bikeType, b.bikeBrands, b.colour,"
//			+ " b.imagePath from Cart c, c.bikeCartSet cc, TwoWheelers b where c.thisCartCustomer =:id ")
//	List<CartBikeDTO> bikeList(Long id);
	
	@Query("SELECT b.bikeId, b.name, b.price, c.bikeQuantity, b.bikeType, b.bikeBrands, b.colour, b.imagePath " +
		       "FROM Cart c JOIN c.bikeSet b " +
		       "WHERE c.thisCartCustomer.id = :id")
		List<CartBikeDTO> bikeList(Long id);
	
//	@Query("select c,p from Cart c join c.partCartSet p where c.thisCartCustomer =:id ")
//	List<Parts> partList(Long id);
	
	
	
//	
//	SELECT b FROM Bikes b, CartBikeColumn c, CustomerCart cc 
//	WHERE c.cartId = cc.id AND c.bikeId = b.id
}

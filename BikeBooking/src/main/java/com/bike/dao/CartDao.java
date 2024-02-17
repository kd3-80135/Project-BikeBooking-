package com.bike.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bike.dto.CartBikeDTO;
import com.bike.dto.CartPartDTO;
import com.bike.entities.Cart;
import com.bike.entities.User;



public interface CartDao extends JpaRepository<Cart, Long>{

	
	@Query("select distinct new com.bike.dto.CartBikeDTO(c.id, b.name, b.price, c.bikeQuantity, b.bikeType, b.bikeBrands, b.colour, b.imagePath) "
			+ "from TwoWheelers b join b.bikeCartSet c where c.thisCartCustomer =:user")
	List<CartBikeDTO> bikeList(User user);
	
	@Query("select distinct new com.bike.dto.CartPartDTO(c.id, p.name, p.price, c.partQuantity, p.imagePath) from Parts p inner join p.partCartSet c where c.thisCartCustomer =:user")
	List<CartPartDTO> partList(User user);
	
	@Query("delete from Cart c where c.thisCartCustomer =:user")
	void deleteUserCart(User user);
}

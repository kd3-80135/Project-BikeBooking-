package com.bike.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bike.entities.Cart;

public interface CartDao extends JpaRepository<Cart, Long>{

	
	
}

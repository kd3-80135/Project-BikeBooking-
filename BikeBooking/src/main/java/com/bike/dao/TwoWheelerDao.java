package com.bike.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bike.entities.TwoWheelers;

public interface TwoWheelerDao extends JpaRepository<TwoWheelers, Long>{
	
}

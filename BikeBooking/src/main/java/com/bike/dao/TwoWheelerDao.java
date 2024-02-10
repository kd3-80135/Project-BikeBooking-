package com.bike.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bike.entities.TwoWheelers;
import com.bike.entities.User;

public interface TwoWheelerDao extends JpaRepository<TwoWheelers, Long>{
	
	@Query("select t from TwoWheelers t where deleteStatus = 0")
	List<TwoWheelers> getAllBikes ();
}

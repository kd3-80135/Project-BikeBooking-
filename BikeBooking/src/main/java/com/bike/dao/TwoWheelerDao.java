package com.bike.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bike.entities.TwoWheelers;

public interface TwoWheelerDao extends JpaRepository<TwoWheelers, Long>{
	
	@Query("select t from TwoWheelers t where deleteStatus = 0")
	List<TwoWheelers> getAllBikes ();
	
	@Query("select b from TwoWheelers b where b.name =:bikeName")
	TwoWheelers findBikeName (String bikeName);
}

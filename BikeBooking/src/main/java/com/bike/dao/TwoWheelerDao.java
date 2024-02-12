package com.bike.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bike.entities.TwoWheelers;

public interface TwoWheelerDao extends JpaRepository<TwoWheelers, Long>{
	
	@Query("select t from TwoWheelers t where deleteStatus = 0")
	List<TwoWheelers> getAllBikes ();
	
	@Query("select b from TwoWheelers b where b.name =:bikeName and deleteStatus = 0")
	TwoWheelers findBikeName (String bikeName);
	
	@Query("select t from TwoWheelers t where deleteStatus = 0 and approveStatus = 1")
	List<TwoWheelers> findBikesAllForUsers ();
}

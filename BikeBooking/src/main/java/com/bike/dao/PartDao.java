package com.bike.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bike.entities.Parts;

public interface PartDao extends JpaRepository<Parts, Long> {
	
	@Query("select p from Parts p where deleteStatus = 0")
	List<Parts> getAllParts ();
	
}

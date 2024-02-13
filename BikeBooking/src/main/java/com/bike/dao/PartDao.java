package com.bike.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bike.entities.Parts;
import com.bike.entities.TwoWheelers;

public interface PartDao extends JpaRepository<Parts, Long> {
	
	@Query("select p from Parts p where deleteStatus = 0")
	List<Parts> getAllParts ();
	
	@Query("select p from Parts p where p.name =: partname and deleteStatus = 0")
	Parts findPartName(String partname);
	
	@Query("select p from Parts p where deleteStatus = 0 and approveStatus = 1")
	List<Parts> findPartsAllForUsers ();
}

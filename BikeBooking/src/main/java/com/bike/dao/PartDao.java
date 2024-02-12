package com.bike.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bike.entities.Parts;

public interface PartDao extends JpaRepository<Parts, Long> {
	
}

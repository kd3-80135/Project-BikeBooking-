package com.bike.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bike.entities.User;

public interface CustomerDao extends JpaRepository<User, Long> {
	
	
}

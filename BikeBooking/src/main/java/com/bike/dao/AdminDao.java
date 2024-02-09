package com.bike.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bike.entities.User;

public interface AdminDao extends JpaRepository<User, Long> {

	@Query("select u from User u where role in (1,2) ")
	List<User> getAllUsers ();
	
}
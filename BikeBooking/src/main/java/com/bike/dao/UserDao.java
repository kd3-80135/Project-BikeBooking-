package com.bike.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.bike.entities.*;

public interface UserDao extends JpaRepository<User, Long> {
	
	User findByEmail(String email);
	
	User findByEmailAndPassword(String email, String Password);
	
}

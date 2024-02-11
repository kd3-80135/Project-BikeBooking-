package com.bike.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bike.entities.*;

public interface UserDao extends JpaRepository<User, Long> {
	
	User findByEmail(String email);
	
	User findByEmailAndPassword(String email, String Password);
	
	@Query("select u from User u where role in (1,2) and deleteStatus = 0")
	List<User> getAllUsers ();
	
	@Query("select t, d from TwoWheelers t join t.dealerSet d where t.deleteStatus=0 and d.id = :id")
	List<TwoWheelers> getTwoWheelers (long id);
	
	@Query("select p, d from Parts p join p.dealerSet d where p.deleteStatus=0 and d.id = :id")
	List<Parts> getParts (long id);
}

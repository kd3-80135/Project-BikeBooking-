package com.bike.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bike.entities.Address;
import com.bike.entities.User;

public interface AddressDao extends JpaRepository<Address, Long>{
	
	List<Address> findByThisUser (User user);
	
}

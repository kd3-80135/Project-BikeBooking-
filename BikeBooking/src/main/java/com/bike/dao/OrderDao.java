package com.bike.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bike.dto.OrderDTO;
import com.bike.entities.Orders;

public interface OrderDao extends JpaRepository<Orders, Long>{

	@Query("select new com.bike.dto.OrderDTO(u.firstName, u.lastName, o.id, o.placedAt, o.deliveredAt,"
			+ "o.cancelledAt) from User u join u.orderSet o")
	List<OrderDTO> getOrderList();
	
}

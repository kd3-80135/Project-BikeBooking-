package com.bike.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bike.dto.OrderDTO;
import com.bike.entities.Orders;
import com.bike.entities.Parts;
import com.bike.entities.TwoWheelers;
import com.bike.entities.User;

public interface OrderDao extends JpaRepository<Orders, Long>{

	@Query("select new com.bike.dto.OrderDTO(u.firstName, u.lastName, o.id, o.placedAt, o.deliveredAt,"
			+ "o.cancelledAt) from User u join u.orderSet o")
	List<OrderDTO> getOrderList();
	
	@Query("select o from Orders o where o.successfullStatus = 0 and o.cancelledStatus = 0 and o.thisCustomer =:user")
	Orders findOrder(User user);
	
	@Query ("select b from Orders o join o.bikeSet b where o.thisCustomer =:user")
	List<TwoWheelers> getOrderBikes (User user);
	
	@Query ("select p from Orders o join o.bikePartSet p where o.thisCustomer =:user")
	List<Parts> getOrderParts (User user);
	
	@Query("select new com.bike.dto.OrderDTO(o.id, o.placedAt, o.deliveredAt, o.cancelledAt) from Orders o where o.thisCustomer =:user")
	List<OrderDTO> findByThisCustomer (User user);
	
	@Query("select u from Orders o join o.thisCustomer u where o.id =:orderId")
	User findUser (Long orderId);
}

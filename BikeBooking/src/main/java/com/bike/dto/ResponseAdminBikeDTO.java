package com.bike.dto;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.bike.entities.BikeBrands;
import com.bike.entities.BikeTypes;
import com.bike.entities.Cart;
import com.bike.entities.Orders;
import com.bike.entities.Role;
import com.bike.entities.User;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ResponseAdminBikeDTO {
	
	private Long id;
	
	private String name;
		
	private int quantity;
	
	private BikeTypes bikeType;
	
	private BikeBrands bikeBrands;
	
	private String colour;
	
	private boolean approveStatus;
	
	private boolean deleteStatus;
	

}

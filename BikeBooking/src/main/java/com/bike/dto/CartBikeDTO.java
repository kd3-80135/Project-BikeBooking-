package com.bike.dto;

import com.bike.entities.BikeBrands;
import com.bike.entities.BikeTypes;

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
public class CartBikeDTO {

	
	private Long cartId;

	
	private String name; 

	
	private double price;
	

	private int bikeQuantity;
	
	
	private BikeTypes bikeType;
	
	
	private BikeBrands bikeBrands;
	
	
	private String colour;
	
	
	private String imagePath;
	
	
}

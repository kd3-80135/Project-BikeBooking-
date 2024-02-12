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
public class BikeDTO {
	
	
	private String name;

	
	private Long id;

	
	private double price;
	

	private int quantity;
	
	
	private BikeTypes bikeType;
	
	
	private BikeBrands bikeBrands;
	
	
	private String description;
	
	
	private String colour;
	


}

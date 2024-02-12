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
public class ResponseDealerBikeDTO {
	
	private Long id;
	
	private String name;
	
	private int quantity;
	
	private BikeTypes bikeType;
	
	private BikeBrands bikeBrands;
	
	private String colour;
	
	private boolean approveStatus;
}

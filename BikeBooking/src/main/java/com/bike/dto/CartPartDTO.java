package com.bike.dto;

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
public class CartPartDTO {

	private Long partId;
	
	private String name;

	private double price;	

	private int partQuantity;	
	
	private String imagePath;
	
}

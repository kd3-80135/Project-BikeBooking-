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
public class PartDTO {
	
	private Long id;
	
	private String name;

	private double price;	

	private String description;	

	private int quantity;	
	
	private String imagePath;
}

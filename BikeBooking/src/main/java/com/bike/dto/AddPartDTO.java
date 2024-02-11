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
public class AddPartDTO {
	
	private String name;

	private double price;	

	private String description;	

	private int quantity;	
	
	private boolean approveStatus;
	

}

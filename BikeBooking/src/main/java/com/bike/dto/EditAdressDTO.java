package com.bike.dto;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;
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
public class EditAdressDTO {
	
	@NotBlank(message = "House No Required")
	@Length(min = 3, max = 20, message = "Invalid Length of House No")
	private String houseNo;
	
	@NotBlank(message = "Apartment Name Required")
	@Length(min = 3, max = 20, message = "Invalid Length of Apartment Name")
	private String apartmentName;
	
	@Length(min = 3, max = 50, message = "Invalid Length of Street")
	private String street;
	
	@NotBlank(message = "Pincode Required")
	@Length(min = 6, max = 6, message = "Invalid Length of pincode")
	private int pincode;
	
	@NotBlank(message = "State Required")
	@Length(min = 4, max = 20, message = "Invalid Length of State")
	private String state;
	
	@NotBlank(message = "City Required")
	@Length(min = 3, max = 50, message = "Invalid Length of City")
	private String city;
	
	@Length(min = 2, max = 50, message = "Invalid Length of Area")
	private String area;	
}

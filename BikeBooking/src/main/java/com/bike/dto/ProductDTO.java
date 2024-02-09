package com.bike.dto;

import javax.persistence.Column;

import com.bike.entities.BaseEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor
@JsonInclude(Include.NON_EMPTY) //values with null value are not included
public class ProductDTO extends BaseEntity{
	private String productName;
	private double price;
	private int quantity;
	private String color;
	private String description;
	
}

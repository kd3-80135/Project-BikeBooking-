package com.bike.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "products")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Product extends BaseEntity{
	@Column(name = "product_name", length=50, unique = true)
	private String productName;
	
	@Column(name="price")
	private double price;
	
	@Column(name="quantity")
	private int quantity;
	
	@Column(name="color")
	private String color;
	
	@Column(name="description")
	private String description;
	
//	private boolean inStock;
//	private int stockQuantity;
	
//	@Column(name = "productImage", length = 400)
//	private String producImage;
  
//	@ManyToOne(fetch = FetchType.LAZY, optional = false)
//	@JoinColumn(name = "category_id", nullable = false)
//	@OnDelete(action = OnDeleteAction.CASCADE)
//	@JsonIgnore
//	private Category productCategory;
//	
}

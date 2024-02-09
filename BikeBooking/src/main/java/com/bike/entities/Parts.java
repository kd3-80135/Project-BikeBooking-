package com.bike.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "bike_parts")
@ToString(exclude = {"dealerSet", "partOrderSet", "partCartSet"})
public class Parts extends BaseEntity{
	
	@Column(name = "bike_part_name", length=30)
	private String name;
	
	@Column(name = "parts_price", nullable = false)
	private double price;
	
	@Column(columnDefinition = "longtext")
	private String description;
	
	@Column(name = "quantity", nullable = false)
	private int quantity;
	
	@JsonInclude(JsonInclude.Include.NON_DEFAULT)
	@Column(name= "approve_status", columnDefinition = "boolean default false")
	private boolean approveStatus;
	
	@JsonInclude(JsonInclude.Include.NON_DEFAULT)
	@Column(name= "delete_status", columnDefinition = "boolean default false")
	private boolean deleteStatus;
	
	@Column(name = "extra_string_column_one", length = 20, columnDefinition = "varchar(100) default 'bike1'")
	private String extraStringColumnOne;
	
	@Column(name = "extra_string_column_two", length = 20, columnDefinition = "varchar(100) default 'bike2'")
	private String extraStringColumnTwo;
	
	@Column(name = "extra_number_column", length = 20, columnDefinition = "integer default 1")
	private Long extraNumberColumn;
	
	@JsonInclude(JsonInclude.Include.NON_DEFAULT)
	@Column(name = "extra_boolean_column", length = 5, columnDefinition = "boolean default false")
	private boolean extraBooleanColumn;
	
	@ManyToMany(cascade = CascadeType.ALL, mappedBy = "bikePartSet")
	private Set<User> dealerSet = new HashSet<>();
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable (name = "order_bikePart_column", joinColumns = @JoinColumn(name="part_id"),
											inverseJoinColumns =@JoinColumn(name="order_id") )
	private Set<Orders> partOrderSet = new HashSet<>();
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable (name = "cart_bikePart_column", joinColumns = @JoinColumn(name="part_id"),
											inverseJoinColumns =@JoinColumn(name="cart_id") )
	private Set<Cart> partCartSet = new HashSet<>();
	
	public void addOrders (Orders order) {
		partOrderSet.add(order);
		order.getBikePartSet().add(this);
	}
	
	public void removeOrders (Orders order) {
		partOrderSet.remove(order);
		order.getBikePartSet().remove(this);
	}
	
	public void addCart (Cart cart) {
		partCartSet.add(cart);
		cart.getBikePartSet().add(this);
	}
	
	public void removeCart (Cart cart) {
		partCartSet.remove(cart);
		cart.getBikePartSet().remove(this);
	}
	
	
	
}

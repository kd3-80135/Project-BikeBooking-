package com.bike.entities;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "customer_cart")
@ToString(exclude = {"bikeSet", "bikePartSet"})
public class Cart extends BaseEntity{
	
	@ManyToMany
	@JoinTable (name = "cart_bike_column", joinColumns = @JoinColumn(name="cart_id"),
	inverseJoinColumns =@JoinColumn(name="bike_id"))
	private Set<TwoWheelers> bikeSet = new HashSet<>();
	
	@ManyToMany
	@JoinTable (name = "cart_part_column", joinColumns = @JoinColumn(name="cart_id"),
	inverseJoinColumns =@JoinColumn(name="part_id") )
	private Set<Parts> bikePartSet = new HashSet<>();
	
	@ManyToOne
	@JoinColumn(name = "customer_id", nullable = false)
	private User thisCartCustomer;
	
	@Column(name = "bike_quantity", nullable = false, columnDefinition = "integer default 0")
	private int bikeQuantity;
	
	@Column(name = "part_quantity", nullable = false, columnDefinition = "integer default 0")
	private int partQuantity;
	
	@Column(name = "created_on", nullable = false)
	private LocalDateTime createdOn;
	
	@Column(name = "last_updated_on", nullable = false)
	private LocalDateTime lastUpdatedOn;	
	
	@Column(name = "extra_string_column_one", length = 20, columnDefinition = "varchar(100) default 'bike1'")
	private String extraStringColumnOne;
	
	@Column(name = "extra_string_column_two", length = 20, columnDefinition = "varchar(100) default 'bike2'")
	private String extraStringColumnTwo;
	
	@Column(name = "extra_number_column", length = 20, columnDefinition = "integer default 1")
	private Long extraNumberColumn;
	
	@JsonInclude(JsonInclude.Include.NON_DEFAULT)
	@Column(name = "extra_boolean_column", length = 5, columnDefinition = "boolean default false")
	private boolean extraBooleanColumn;

	public Cart(User thisCartCustomer, int bikeQuantity, int partQuantity, LocalDateTime createdOn, LocalDateTime lastUpdatedOn,
			String extraStringColumnOne, String extraStringColumnTwo, Long extraNumberColumn,
			boolean extraBooleanColumn) {
		this.thisCartCustomer = thisCartCustomer;
		this.bikeQuantity = bikeQuantity;
		this.partQuantity = partQuantity;
		this.createdOn = createdOn;
		this.lastUpdatedOn = lastUpdatedOn;
		this.extraStringColumnOne = extraStringColumnOne;
		this.extraStringColumnTwo = extraStringColumnTwo;
		this.extraNumberColumn = extraNumberColumn;
		this.extraBooleanColumn = extraBooleanColumn;
	}
	
	

}

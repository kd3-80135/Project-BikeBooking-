package com.bike.entities;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
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
@Table(name = "customer_cart")
@ToString(exclude = {"bikeSet", "bikePartSet"})
public class Cart extends BaseEntity{
	
	@ManyToMany(cascade = CascadeType.ALL, mappedBy = "bikeCartSet")
	private Set<TwoWheelers> bikeSet = new HashSet<>();
	
	@ManyToMany(cascade = CascadeType.ALL, mappedBy = "partCartSet")
	private Set<Parts> bikePartSet = new HashSet<>();
	
	@OneToOne
	@JoinColumn(name = "user_cart_id", nullable = false)
	@MapsId
	private User thisCartUser;
	
	@Column(name = "bike_quantity", nullable = false)
	private int bikeQuantity;
	
	@Column(name = "part_quantity", nullable = false)
	private int partQuantity;
	
	@Column(name = "created_on", nullable = false)
	private LocalDate createdOn;
	
	@Column(name = "last_updated_on", nullable = false)
	private LocalDate lastUpdatedOn;	
	
	@Column(name = "extra_string_column_one", length = 20, columnDefinition = "varchar(100) default 'bike1'")
	private String extraStringColumnOne;
	
	@Column(name = "extra_string_column_two", length = 20, columnDefinition = "varchar(100) default 'bike2'")
	private String extraStringColumnTwo;
	
	@Column(name = "extra_number_column", length = 20, columnDefinition = "integer default 1")
	private Long extraNumberColumn;
	
	@JsonInclude(JsonInclude.Include.NON_DEFAULT)
	@Column(name = "extra_boolean_column", length = 5, columnDefinition = "boolean default false")
	private boolean extraBooleanColumn;
	
	

}
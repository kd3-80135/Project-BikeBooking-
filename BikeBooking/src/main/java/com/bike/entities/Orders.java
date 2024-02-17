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
@Table(name = "Orders")
@ToString(exclude = {"bikeSet", "bikePartSet", "thisCustomer"})
@AllArgsConstructor
public class Orders extends BaseEntity {
	
	@ManyToOne
	@JoinColumn(name = "customer_id", nullable = false)
	private User thisCustomer;
	
	@ManyToMany
	@JoinTable (name = "order_bike_column", joinColumns = @JoinColumn(name="bike_id"),
			inverseJoinColumns =@JoinColumn(name="order_id"))
	private Set<TwoWheelers> bikeSet = new HashSet<>();
	
	@ManyToMany
	@JoinTable (name = "order_part_column", joinColumns = @JoinColumn(name="part_id"),
			inverseJoinColumns =@JoinColumn(name="order_id") )
	private Set<Parts> bikePartSet = new HashSet<>();
	
	@JsonInclude(JsonInclude.Include.NON_DEFAULT)
	@Column(name = "cancelled_status", columnDefinition = "boolean default false")
	private boolean cancelledStatus;
	
	@Column(name="placed_at")
	private LocalDateTime placedAt;
	
	@Column(name = "delivered_at")
	private LocalDateTime deliveredAt;
	
	@Column(name = "cancelled_at")
	private LocalDateTime cancelledAt;
	
	@JsonInclude(JsonInclude.Include.NON_DEFAULT)
	@Column(name= "successful_status", columnDefinition = "boolean default false")
	private boolean successfullStatus;
	
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

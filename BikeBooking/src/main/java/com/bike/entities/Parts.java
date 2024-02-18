package com.bike.entities;

import java.util.HashSet;
import java.util.Objects;
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
	
	@Column(name = "part_image_path")
	private String imagePath;
	
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
	
	@ManyToMany(cascade = CascadeType.ALL, mappedBy = "bikePartSet")
	private Set<Orders> partOrderSet = new HashSet<>();
	
	@ManyToMany(cascade = CascadeType.ALL, mappedBy = "bikePartSet")
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(approveStatus, deleteStatus, description, extraBooleanColumn,
				extraNumberColumn, extraStringColumnOne, extraStringColumnTwo, imagePath, name, price, quantity);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Parts other = (Parts) obj;
		return approveStatus == other.approveStatus && deleteStatus == other.deleteStatus
				&& Objects.equals(description, other.description) && extraBooleanColumn == other.extraBooleanColumn
				&& Objects.equals(extraNumberColumn, other.extraNumberColumn)
				&& Objects.equals(extraStringColumnOne, other.extraStringColumnOne)
				&& Objects.equals(extraStringColumnTwo, other.extraStringColumnTwo)
				&& Objects.equals(imagePath, other.imagePath) && Objects.equals(name, other.name)
				&& Double.doubleToLongBits(price) == Double.doubleToLongBits(other.price) && quantity == other.quantity;
	}
	
	
	
}

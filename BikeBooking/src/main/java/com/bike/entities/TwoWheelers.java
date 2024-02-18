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
@Table(name = "bikes")
@ToString(exclude = {"dealerSet", "bikeOrderSet", "bikeCartSet"})
public class TwoWheelers extends BaseEntity{
	
	@Column( name =" bike_name", length = 30)
	private String name;
	
	@Column(name = "price", nullable = false)
	private double price;
	
	@Column(name = "quantity", nullable = false)
	private int quantity;
	
	@Column(name = "bike_type", length = 10)
	private BikeTypes bikeType;
	
	@Column(name = "bike_brands", length = 20)
	private BikeBrands bikeBrands;
	
	@Column(columnDefinition = "longtext")
	private String description;
	
	@Column(name = "colour", length = 20)
	private String colour;
	
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
	
	@ManyToMany(cascade = CascadeType.ALL, mappedBy = "bikeSet")
	private Set<User> dealerSet = new HashSet<>();
	
	@ManyToMany(cascade = CascadeType.ALL, mappedBy = "bikeSet")
	private Set<Orders>  bikeOrderSet = new HashSet<>();
	
	@ManyToMany(cascade = CascadeType.ALL, mappedBy = "bikeSet")
	private Set<Cart>  bikeCartSet = new HashSet<>();
	
	@Column(name = "bike_image_path")
	private String imagePath;
	
	public void addOrders (Orders order) {
		bikeOrderSet.add(order);
		order.getBikeSet().add(this);
	}
	
	public void removeOrders (Orders order) {
		bikeOrderSet.remove(order);
		order.getBikeSet().remove(this);
	}
	
	public void addCart (Cart cart) {
		bikeCartSet.add(cart);
		cart.getBikeSet().add(this);
	}
	
	public void removeCart (Cart cart) {
		bikeCartSet.remove(cart);
		cart.getBikeSet().add(this);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(approveStatus, bikeBrands, bikeType, colour, dealerSet, description,
				extraBooleanColumn, extraNumberColumn, extraStringColumnOne, extraStringColumnTwo, imagePath, name,
				price, quantity);
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
		TwoWheelers other = (TwoWheelers) obj;
		return approveStatus == other.approveStatus && bikeBrands == other.bikeBrands && bikeType == other.bikeType
				&& Objects.equals(colour, other.colour) && Objects.equals(dealerSet, other.dealerSet)
				&& Objects.equals(description, other.description) && extraBooleanColumn == other.extraBooleanColumn
				&& Objects.equals(extraNumberColumn, other.extraNumberColumn)
				&& Objects.equals(extraStringColumnOne, other.extraStringColumnOne)
				&& Objects.equals(extraStringColumnTwo, other.extraStringColumnTwo)
				&& Objects.equals(imagePath, other.imagePath) && Objects.equals(name, other.name)
				&& Double.doubleToLongBits(price) == Double.doubleToLongBits(other.price) && quantity == other.quantity;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}

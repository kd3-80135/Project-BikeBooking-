package com.bike.entities;


import java.util.ArrayList;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "user_data")
@ToString(exclude = "myAddresses") //exclude
//@JsonInclude (Include.NON_EMPTY)//will not include any null valued or empty properties during ser.
public class User extends BaseEntity{
	
	@Column(name = "first_name", length = 20)
	private String firstName;
	
	@Column(name = "last_name", length = 20)
	private String lastName;
	
	@Column(name = "email", unique = true)
	private String email;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "address_id")
	@OneToMany(mappedBy = "thisUser", cascade = CascadeType.ALL, orphanRemoval = true)
	private java.util.List<Address> myAddresses = new ArrayList<>();
	
	private String mobile;	
	
	private Role role;
	
	@Column(name = "delete_status", columnDefinition = "boolean default false")
	private boolean deleteStatus;
	
	@Column(name= "block_status", columnDefinition = "boolean default false")
	private boolean blockStatus;
	
	@Column(name = "extra_string_column_one", length = 20, columnDefinition = "varchar(100) default 'bike1'")
	private String extraStringColumnOne;
	
	@Column(name = "extra_string_column_two", length = 20, columnDefinition = "varchar(100) default 'bike2'")
	private String extraStringColumnTwo;
	
	@Column(name = "extra_number_column", length = 20, columnDefinition = "integer default 1")
	private Long extraNumberColumn;
	
	@Column(name = "extra_boolean_column", length = 5, columnDefinition = "boolean default false")
	private Long extraBooleanColumn;
	
	public void addAddress(Address addr) {
	myAddresses.add(addr);
	addr.setThisUser(this);
	}

	public void removeAddress(Address addr) {
	myAddresses.remove(addr);
	addr.setThisUser(null);
	}
	
	
}

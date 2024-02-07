package com.bike.entities;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import java.util.ArrayList;

import org.hibernate.annotations.Columns;
import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonInclude;

import antlr.collections.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
	
	public void addAddress(Address addr) {
	myAddresses.add(addr);
	addr.setThisUser(this);
	}

	public void removeAddress(Address addr) {
	myAddresses.remove(addr);
	addr.setThisUser(null);
	}
	
	
}

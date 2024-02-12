package com.bike.dto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import com.bike.entities.Address;
import com.bike.entities.Cart;
import com.bike.entities.Orders;
import com.bike.entities.Parts;
import com.bike.entities.Role;
import com.bike.entities.TwoWheelers;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ResponseUserDTO {
	
	private Long id;
	
	@NotBlank(message = "first name is required")
	@Length(min = 3, max = 20, message = "Invalid Length of First Name")
	private String firstName;
	
	@NotBlank
	@Length(min = 3, max = 20, message = "Invalid Length of Last Name")
	private String lastName;
	
	@NotBlank(message = "Email Required")
	@Length(min = 10, max = 20, message = "Invalid Length of Email")
	@Email(message= "Invalid Email")
	private String email;
	
	@NotBlank(message = "Password Required")
	@Length(min = 8, max = 20, message = "Invalid Length of password")
	@Pattern(regexp = "((?=.*\\d)(?=.*[a-z])(?=.*[#@$*]).{5,20})",message = "Invalid Password !")
	private String password;
	
	@NotBlank
	@Length(min = 10, max = 10, message = "Invalid Length's First Name")
	private String mobile;	
	
	private Role role;
	
	private boolean deleteStatus;
	
	private boolean blockStatus;
	
	
	
	
	

}

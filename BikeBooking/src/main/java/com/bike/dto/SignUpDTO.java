package com.bike.dto;


import org.hibernate.validator.constraints.Length;

import com.bike.entities.Role;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
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
public class SignUpDTO {
	
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
}

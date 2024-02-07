package com.bike.dto;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

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
public class SignInDTO {
	@Column(unique = true)
	@NotBlank(message = "Email Required")
	@Length(min = 10, max = 20, message = "Invalid Length of Email")
	@Email(message= "Invalid Email")
	private String email;
	
	@NotBlank(message = "Password Required")
	@Length(min = 8, max = 20, message = "Invalid Length of password")
	@Pattern(regexp = "((?=.*\\d)(?=.*[a-z])(?=.*[#@$*]).{5,20})",message = "Invalid Password !")
	private String password;

}

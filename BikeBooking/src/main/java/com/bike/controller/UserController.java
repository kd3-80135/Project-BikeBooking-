package com.bike.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bike.dto.EditProfileDTO;
import com.bike.dto.SignInDTO;
import com.bike.dto.SignUpDTO;
import com.bike.service.UserService;


import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Validated
public class UserController {

	@Autowired
	private UserService userService;
	
	public UserController(){
		System.out.println("in def ctor " + getClass());
	}
	
	@PostMapping("/register")
	public ResponseEntity<?> RegisterUser(@PathVariable int role, @RequestBody @Valid SignUpDTO signUp) {
		System.out.println("In RegisterUser method of " + getClass().getName());
		return userService.registerUser(role, signUp);
	}
	
	@PostMapping("/signin")
	public ResponseEntity<?> AuthenticateUser(@RequestBody @Valid SignInDTO signin){
		System.out.println("In Authenticate method of " + getClass().getName());
		return userService.SignInUser(signin);
	}
	
	@GetMapping ("/edit/{id}")
	public EditProfileDTO EditProfile(@PathVariable @NotNull Long id){
		System.out.println("In Edit Profile method of Customer Controller.");
		return userService.EditProfileService(id);
	}
	
	@PutMapping ("/update/{id}")
	public ResponseEntity<?> UpdateProfile (@RequestBody @NotNull EditProfileDTO customerDTO, @PathVariable @NotNull Long id){
		System.out.println("In Update Profile method of Customer Controller.");
		return userService.UpdateProfileService(customerDTO, id);
	}
	
	
	
}

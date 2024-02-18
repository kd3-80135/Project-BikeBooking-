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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bike.dto.EditAdressDTO;
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
	
	@PostMapping("/register/{role}")
	public ResponseEntity<?> RegisterUser(@PathVariable int role, @RequestBody @Valid EditProfileDTO signUp) {
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
		System.out.println("In Edit Profile method of " + getClass().getName());
		return userService.EditProfileService(id);
	}
	
	@PutMapping ("/update/{id}")
	public ResponseEntity<?> UpdateProfile (@RequestBody @NotNull EditProfileDTO customerDTO, @PathVariable @NotNull Long id){
		System.out.println("In Update Profile method of "  + getClass().getName());
		return userService.UpdateProfileService(customerDTO, id);
	}
	
	@GetMapping ("/editAddress/{id}")
	public EditAdressDTO EditAddress (@PathVariable @NotNull Long id){
		System.out.println("In Edit Address method of "  + getClass().getName());
		return userService.EditAddressService(id);
	}
	
	@PutMapping ("/updateAddress/{id}")
	public ResponseEntity<?> updateAddress (@PathVariable @NotNull Long id, @RequestBody @NotNull EditAdressDTO addrDTO){
		System.out.println("In Upadate Address method of "  + getClass().getName());
		return userService.updateAddressService(id, addrDTO);
	}
	
	@PostMapping("/forgotPassword/{email}")
    public ResponseEntity<String> forgotPassword(@PathVariable @Valid String email) {
		System.out.println("In forgotPassword method of "  + getClass().getName());
		return userService.forgotPasswordService(email);
    }
	
	@PostMapping("verifyOTP/{otp}/{email}")
	public ResponseEntity<String> verifyOTP(@PathVariable @Valid String otp, @PathVariable @Valid String email) {
		System.out.println("In verifyOTP method of "  + getClass().getName());
		return userService.verifyOTPService (otp,email);
    }

    @PostMapping("/resetPassword")
    public ResponseEntity<String> resetPassword(@RequestBody @Valid SignInDTO signInDTO) {
    	System.out.println("In resetPassword method of " + getClass().getName());
    	return userService.resetPasswordService(signInDTO);
    }
	
	
	
	
	
}

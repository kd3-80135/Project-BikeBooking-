package com.bike.service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.http.ResponseEntity;

import com.bike.dto.EditProfileDTO;
import com.bike.dto.SignInDTO;
import com.bike.dto.SignUpDTO;

public interface UserService {

	ResponseEntity<?> SignInUser(SignInDTO signin);

	ResponseEntity<?> registerUser(int role, SignUpDTO signUp);
	
	EditProfileDTO EditProfileService(Long id);

	ResponseEntity<?> UpdateProfileService(EditProfileDTO editDTO, Long id);
	
}

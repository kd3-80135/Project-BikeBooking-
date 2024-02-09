package com.bike.service;


import org.springframework.http.ResponseEntity;

import com.bike.dto.EditAdressDTO;
import com.bike.dto.EditProfileDTO;
import com.bike.dto.SignInDTO;

public interface UserService {

	ResponseEntity<?> SignInUser(SignInDTO signin);

	ResponseEntity<?> registerUser(int role, EditProfileDTO signUp);
	
	EditProfileDTO EditProfileService(Long id);

	ResponseEntity<?> UpdateProfileService(EditProfileDTO editDTO, Long id);

	EditAdressDTO EditAddressService(Long id);

	ResponseEntity<?> updateAddressService(Long id, EditAdressDTO addrDTO);
	
}

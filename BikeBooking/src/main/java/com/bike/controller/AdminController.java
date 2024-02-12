package com.bike.controller;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bike.service.AdminService;

@RestController
@RequestMapping ("/admin")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Validated
public class AdminController {
	
	public AdminController() {
		System.out.println("In ctor of " + getClass().getName());
	}
	
	@Autowired
	private AdminService adminService;
	
	@GetMapping("/userList")
	public ResponseEntity<?> getUserList(){
		System.out.println("In getUserList method of " + getClass().getName());
		return adminService.getUserListService();
	}
	
	@DeleteMapping ("/deleteUser/{id}")
	public ResponseEntity<?> deleteUser (@PathVariable @Valid long id){
		System.out.println("In deleteUser method of " + getClass().getName());
		return adminService.deleteUserService(id);
	}
	
	@PutMapping("/blockUser/{id}")
	public ResponseEntity<?> blockUser (@PathVariable @Valid long id){
		System.out.println("In blockUser method of " + getClass().getName());
		return adminService.blockUserService(id);
	}
	
	@PutMapping("/unBlockUser/{email}")
	public ResponseEntity<?> unBlockUser (@PathVariable @Valid String email){
		System.out.println("In blockUser method of " + getClass().getName());
		return adminService.unBlockUserService(email);
	}
	
	@GetMapping("/bikes")
	public ResponseEntity<?> getBikeList (){
		System.out.println("In getBikeList method of " + getClass().getName());
		return adminService.getBikeListService();
	}
	
	@GetMapping("/parts")
	public ResponseEntity<?> getPartList (){
		System.out.println("In getPartList method of " + getClass().getName());
		return adminService.getPartListService();
	}
	
	
	
	
	
}

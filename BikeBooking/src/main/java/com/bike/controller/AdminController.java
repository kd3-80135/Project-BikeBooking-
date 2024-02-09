package com.bike.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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
	
	
	
	
}

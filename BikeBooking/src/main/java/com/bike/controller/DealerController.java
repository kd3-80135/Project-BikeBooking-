package com.bike.controller;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bike.dto.AddBikeDTO;
import com.bike.dto.AddPartDTO;
import com.bike.service.DealerService;

@RestController
@RequestMapping ("/dealer")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Validated
public class DealerController {
	
	public DealerController() {
		System.out.println("In ctor of " + getClass().getName());
	} 
	
	@Autowired
	private DealerService dealerService;
	
	@GetMapping("/bikeList/{id}")
	public ResponseEntity<?> GetBikeList(@PathVariable @Valid long id){
		System.out.println("In bikeList method "+ getClass().getName());
		return dealerService.getBikeListService(id);
	
	}
	
	@PostMapping("/addBike/{id}")
	public ResponseEntity<?> AddBike(@PathVariable @Valid long id, @RequestBody @Valid AddBikeDTO addBikeDTO){
		System.out.println("In addBike method of "+ getClass().getName());
		return dealerService.addBikeService(id, addBikeDTO);
	}
	
	@GetMapping("/partList/{id}")
	public ResponseEntity<?> GetPartList(@PathVariable @Valid long id){
		System.out.println("In partList method of "+ getClass().getName());
		return dealerService.getPartListService(id);
	
	}
	
	@PostMapping("/addPart/{id}")
	public ResponseEntity<?> AddPart(@PathVariable @Valid long id, @RequestBody @Valid AddPartDTO addPartDTO){
		System.out.println("In addPart method of "+ getClass().getName());
		return dealerService.addPartService(id, addPartDTO);
	}
	
	@DeleteMapping("/deleteBike/{bikeId}")
	public ResponseEntity<?> deleteBike (@PathVariable @Valid long bikeId){
		System.out.println("In deleteBike method of "+ getClass().getName());
		return dealerService.deleteBikeService(bikeId);
	}
	
	@DeleteMapping("/deletePart/{partId}")
	public ResponseEntity<?> deletePart (@PathVariable @Valid long partId){
		System.out.println("In deletePart method of "+ getClass().getName());
		return dealerService.deletePartService(partId);
	}
	
	
	
}

package com.bike.controller;


import java.io.IOException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
	
	@GetMapping ("/editBike/{bikeId}")
	public ResponseEntity<?> editBike (@PathVariable @Valid long bikeId){
		System.out.println("In editBike method of " + getClass().getName());
		return dealerService.editBikeService(bikeId);
	}
	
	@PutMapping ("/updateBike/{bikeId}")
	public ResponseEntity<?> updateBike (@PathVariable @Valid long bikeId, @RequestBody @Valid AddBikeDTO bikeDTO){
		System.out.println("In updateBike method of " + getClass().getName());
		return dealerService.updateBikeService(bikeId, bikeDTO);
	}
	
	@GetMapping ("/editPart/{partId}")
	public ResponseEntity<?> editPart (@PathVariable @Valid long partId){
		System.out.println("In editPart method of " + getClass().getName());
		return dealerService.editPartService (partId);
	}
	
	@PutMapping ("/updatePart/{partId}")
	public ResponseEntity<?> updatePart (@PathVariable @Valid long partId, @RequestBody @Valid AddPartDTO partDTO){
		System.out.println("In updatePart method of " + getClass().getName());
		return dealerService.updatePartService(partId, partDTO);
	}
	
	@PostMapping(value ="/bikeImages/{bikeId}", consumes = "multipart/form-data")
	public ResponseEntity<?> uploadBikeImageToFolderPathToDB (@PathVariable Long bikeId, @RequestParam MultipartFile image) throws IOException{
		System.out.println(" In uploadPartImageToFolderPathToDB method of " + getClass().getName());
		return dealerService.uploadBikeImageToFolderPathToDBService(bikeId, image);
	}
	
	@PostMapping(value ="/partImages/{partId}", consumes = "multipart/form-data")
	public ResponseEntity<?> uploadPartImageToFolderPathToDB (@PathVariable Long partId, @RequestParam MultipartFile image) throws IOException{
		System.out.println(" In uploadPartImageToFolderPathToDB method of " + getClass().getName());
		return dealerService.uploadBikeImageToFolderPathToDBService(partId, image);
	}
	
}

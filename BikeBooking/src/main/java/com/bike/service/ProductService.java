package com.bike.service;

import org.springframework.http.ResponseEntity;

import com.bike.dto.AddProductDTO;
import com.bike.dto.DeleteProductDTO;
import com.bike.dto.EditProductDTO;

public interface ProductService {
	
	AddProductDTO AddProductService(Long id);
	ResponseEntity<?> AddProduct(AddProductDTO addDTO, Long id);
	
	EditProductDTO EditProductService(Long id);
	ResponseEntity<?> EditProduct(EditProductDTO editDTO, Long id);
	
	DeleteProductDTO DeleteProductService(Long id);
	ResponseEntity<?> DeleteProduct(E);
	
}

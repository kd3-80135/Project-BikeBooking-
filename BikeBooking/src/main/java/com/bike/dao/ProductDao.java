package com.bike.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bike.entities.Product;

public interface ProductDao extends JpaRepository<Product, Long>{

}

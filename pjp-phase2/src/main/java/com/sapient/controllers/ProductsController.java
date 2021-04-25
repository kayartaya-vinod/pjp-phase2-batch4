package com.sapient.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sapient.dao.ProductsDao;
import com.sapient.entity.Product;

@CrossOrigin
@RestController
@RequestMapping("/api/products")
public class ProductsController {
	
	ProductsDao dao = new ProductsDao();

	// function mapped to a url /api/products
	// executed when the client makes GET requests
	@GetMapping
	public List<Product> getAllProducts() {
		return dao.getAll();
	}
	
	@GetMapping("/brand/{brand}")
	public List<Product> getProductsByBrand(@PathVariable String brand) {
		return dao.getByBrand(brand);
	}
	
	@GetMapping("/{id}")
	public Product getProductById(@PathVariable Integer id) {
		return dao.getById(id);
	}
}

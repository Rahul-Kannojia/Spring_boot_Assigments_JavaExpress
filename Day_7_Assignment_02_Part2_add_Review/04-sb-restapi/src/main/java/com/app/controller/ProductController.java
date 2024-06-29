package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.entities.Product;
import com.app.services.ProductService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("api/v1/product")
@Slf4j
public class ProductController {

	@Autowired
	private ProductService productService;

	@PostMapping
	public void createProduct(@RequestBody Product product) {
		log.info("ProductController ::createProduct {} ", product.getName());
		productService.createProduct(product);
	}

	@GetMapping("{pid}")
	public Product fetchProduct(@PathVariable(name = "pid") Long prodId) {
		log.info("ProductController ::fetchProduct {} ", prodId);
		return productService.fetchProductInfo(prodId);
	}

	@PutMapping("{pid}")
	public void updateProduct(@PathVariable(name = "pid") Long prodId, @RequestBody Product inputProduct) {
		log.info("ProductController ::updateProduct {} {} {} ", prodId, inputProduct.getCategory(),
				inputProduct.getName());
		productService.updateProduct(prodId, inputProduct);
	}

	@DeleteMapping("{pid}")
	public void deleteProduct(@PathVariable(name = "pid") Long prodId) {
		log.info("ProductController ::deleteProduct {} ", prodId);
		productService.deleteProduct(prodId);
	}
}

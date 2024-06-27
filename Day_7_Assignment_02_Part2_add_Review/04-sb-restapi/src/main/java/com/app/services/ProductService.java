package com.app.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.entities.Product;
import com.app.repositories.ProductRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProductService implements IProductService {

	@Autowired
	private ProductRepository productRepository;

	public void createProduct(Product product) {
		log.info("ProductService :: createProduct {}", product.toString());
		product.setIsStock(true);
		product.setBarCode(UUID.randomUUID().toString());
		productRepository.save(product);
		log.info("Product saved Successfully in Database");
	}

	public Product fetchProductInfo(Long prodId) {
		log.info("ProductService :: fetchProductInfo {}", prodId);
		return productRepository.findById(prodId).orElseThrow(() -> new RuntimeException("Product Not Available"));
	}

	public void updateProduct(Long prodId, Product inputProduct) {
		log.info("ProductService :: updateProduct {} {} {}", prodId, inputProduct.getName(),
				inputProduct.getCategory());
		Product product = fetchProductInfo(prodId);
		if (product.getCategory().getId() == inputProduct.getCategory().getId()) {
			product.setName(inputProduct.getName());
			product.setPrice(inputProduct.getPrice());
			product.setDescription(inputProduct.getDescription());
			product.setQuantity(inputProduct.getQuantity());
			product.setBarCode(UUID.randomUUID().toString());
			product.setIsStock(true);
			productRepository.save(product);
		} else {
			log.error("Category belong to given Product is not available");
			throw new RuntimeException("Product does not belongs to given Category ");
		}
	}

	public void deleteProduct(Long prodId) {
		if(productRepository.existsById(prodId)) {
			log.info("ProductService :: fetchProductInfo {}", prodId);
			productRepository.deleteById(prodId);
			log.info("ProductService :: fetchProductInfo :: Product with given ID is deleted successfully");
		}else {
			log.error("ProductService :: fetchProductInfo :: Product is not available with this ID {}",prodId);
			throw new RuntimeException("Product with given ID is not available");
		}
				
	}
}

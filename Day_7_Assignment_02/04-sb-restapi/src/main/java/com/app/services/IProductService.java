package com.app.services;

import com.app.entities.Product;

public interface IProductService {

	public void createProduct(Product product);
	public Product fetchProductInfo(Long prodId);
	public void updateProduct(Long prodId, Product inputProduct);
	public void deleteProduct(Long prodId);
}

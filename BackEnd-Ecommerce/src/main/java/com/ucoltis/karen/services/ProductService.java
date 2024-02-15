package com.ucoltis.karen.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ucoltis.karen.models.Product;
import com.ucoltis.karen.repository.ProductRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProductService {
	
	@Autowired
	ProductRepository productRepository;
	
	//crear
	public Product createProduct(String name, Double price, Integer amount, String description, String imageUrl) {
		Product newProduct = new Product(0, name, price, amount, description, imageUrl);
		
		return productRepository.save(newProduct);
	}

	
	//leer por id 
	public Product readProduct(Integer id) {
		return productRepository.findById(id).get();
	}
	//leerTodos
	public List<Product> readAllProducts(){
		return productRepository.findAll();
	}
	
	//update
	public Product updateProduct(Product product) {
		Product updateItem = productRepository.findById(product.getProductId()).get();
		updateItem.setProductName(product.getProductName());
		updateItem.setPrice(product.getPrice());
		updateItem.setAmount(product.getAmount());
		updateItem.setDescription(product.getDescription());
		updateItem.setImageUrl(product.getImageUrl());
		
		return productRepository.save(updateItem);
		
	}
	
	//delete
	
	public String  deleteItem(Integer id) {
		Product item = productRepository.findById(id).get();
		productRepository.delete(item);
		return "Item ha sido borrado";
	}

}

package com.ucoltis.karen.controllers;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ucoltis.karen.models.Product;
import com.ucoltis.karen.services.ProductService;

import lombok.AllArgsConstructor;

@RestController  // servicio web 
@RequestMapping("product")  //va a ser una api utilizando http
@CrossOrigin("*")
@AllArgsConstructor
public class ProductController {
	//Modelo-V(frontend/otro servicio/ web/ movil)-Controller
		//puente de union entre backend y frontend - integracion de aplicaciones
		
		//integrar 
		@Autowired
		private ProductService producService;
		
		//crear / post
		@PostMapping("/create")
		public Product create(@RequestBody LinkedHashMap<String , Object> body) {
			String name = (String)body.get("name");
			Double price = (Double) body.get("price");
			Integer amount = (Integer)body.get("amount");
			String description = (String)body.get("description");
			String imageUrl = (String)body.get("imageUrl");
			return producService.createProduct(name, price, amount, description, imageUrl);
		}
		//leer / get --http // obtener el id del product
		@GetMapping("/read")
		public Product read(@RequestBody LinkedHashMap<String, Integer> body) {
			return producService.readProduct(body.get("id"));	
		}
		//leer obtener todos los datos
		@GetMapping("/read/all")
		public List<Product> readAll(){
			return producService.readAllProducts();
		}
		
		//update Put
		@PutMapping("/update")
		public Product update(@RequestBody Product newProducto) {
			return producService.updateProduct(newProducto);
		}
		
		//delete
		@DeleteMapping("/delete")
		public String delete(@RequestBody LinkedHashMap<String, Integer> body){
			return producService.deleteItem(body.get("id"));
			
		}

}

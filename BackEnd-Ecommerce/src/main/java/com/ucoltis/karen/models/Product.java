package com.ucoltis.karen.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="products")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	@Column(name="product_id")
	private Integer productId;
	
	@Column(name="produc_name")
	private String productName;
	
	private Double price;
	
	private Integer amount;
	
	private String description;
	
	private String imageUrl;
	
	
	
	

}

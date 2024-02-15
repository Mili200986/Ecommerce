package com.ucoltis.karen.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ucoltis.karen.models.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{

}

package com.ucoltis.karen.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ucoltis.karen.models.Receipt;

@Repository
public interface ReceiptRepository extends JpaRepository<Receipt, Integer>{

}

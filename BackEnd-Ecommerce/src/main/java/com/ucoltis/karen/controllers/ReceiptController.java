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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ucoltis.karen.models.Product;
import com.ucoltis.karen.models.Receipt;
import com.ucoltis.karen.services.ReceiptService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("receipts")
@CrossOrigin
@AllArgsConstructor
public class ReceiptController {

	@Autowired
	private ReceiptService receiptService;
	
	@PostMapping("/create")
	public Receipt create(@RequestBody ReceiptRegisterObject newRegister) {
		Integer userId = newRegister.userId;
		List<Product> products = newRegister.items;
		
		return receiptService.createReceipt(userId, products);
	}
	@GetMapping("/read")
	public Receipt read(@RequestBody LinkedHashMap<String, Integer>body) {
		return receiptService.readReceipt(body.get("id"));
	}
	@GetMapping("/readuser")
	public List<Receipt> readUser(@RequestParam(name="id") Integer id) {
		return receiptService.getReceiptByUser(id);
	}
	@PutMapping("/update")
	public Receipt update(@RequestBody Receipt newReceipt) {
		return receiptService.updateReceipt(newReceipt);
	}
	@DeleteMapping("/delete")
	public String delete(@RequestBody LinkedHashMap<String, Integer> body) {
		return receiptService.deleteReceipt(body.get("id"));
	}
	
	class ReceiptRegisterObject{
		public Integer userId;
		public List<Product> items;
		public Integer amountOfItems;
	}
}

package com.ucoltis.karen.services;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ucoltis.karen.models.Product;
import com.ucoltis.karen.models.Receipt;
import com.ucoltis.karen.models.User;
import com.ucoltis.karen.repository.ProductRepository;
import com.ucoltis.karen.repository.ReceiptRepository;
import com.ucoltis.karen.repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
@Transactional
public class ReceiptService {
	//inyeccion de dependencia de los objetos que estan relacionados
	
		@Autowired
		ProductRepository productRepository;
		@Autowired
		ReceiptRepository receiptRepository;
		@Autowired
		UserRepository userRepository;
		
		//create un receipt
		//@Transactional(rollbackOn = {SQLException.class} )
		public Receipt createReceipt (String email, List<Product> product, Double total) {
			User userReceipt = userRepository.getByEmail(email).get();
			Receipt newReceipt = new Receipt(userReceipt, product, total, getDateTimeWidthFormat(), product.size());
			return receiptRepository.save(newReceipt);
		}//end receipt
		
		//Create receipt products
		public Receipt createReceipt (Integer id, List<Product> product) {
			User userReceipt = userRepository.findById(id).get();
			List<Product> itemsReceipt = new ArrayList<>();
			Double total = 0.0;
			Integer amountOfItems =0;
			
			for (Product item: product ) {
				Product addItem = productRepository.findById(item.getProductId()).get();
				addItem.setAmount(item.getAmount());
				total += item.getPrice() * item.getAmount();
				amountOfItems +=item.getAmount();
				itemsReceipt.add(addItem);
			}
			DecimalFormat df = new DecimalFormat("0,00");
			total=Double.parseDouble(df.format(total));
			
			Receipt newReceipt = new Receipt(userReceipt,itemsReceipt, total, getDateTimeWidthFormat(), amountOfItems);
			
			return receiptRepository.save(newReceipt);
		}
		
		//leerlo
		public Receipt readReceipt(Integer id) {
			return receiptRepository.findById(id).get();
		}
		//leer los receipt por users 
		public List<Receipt> getReceiptByUser(Integer idUser){
			List<Receipt> receipts = receiptRepository.findAll();
			List<Receipt> result = new ArrayList<>();
			for(Receipt item:receipts) {
				if (item.getUser().getUserId() == idUser) {
					result.add(item);
				}
			}
			return result;
		}
		
		//update
		public Receipt updateReceipt(Receipt updateItem) {
			Receipt updateReceipt = receiptRepository.findById(updateItem.getReceiptNumber()).get();
			updateReceipt.setAmountOfItems(updateItem.getAmountOfItems());
			updateReceipt.setDateTime(updateItem.getDateTime());
			updateReceipt.setProducts(updateItem.getProducts());
			updateReceipt.setTotal(updateItem.getTotal());
			updateReceipt.setUser(updateItem.getUser());
			
			return receiptRepository.save(updateReceipt);
			
		}
		
		//delete
		public String deleteReceipt(Integer id) {
			receiptRepository.delete(receiptRepository.findById(id).get());
			return "Receipt fue borrado";
		}

		//Metodo para fecha y hora
		public String getDateTimeWidthFormat() {
			DateTimeFormatter format= DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
			return LocalDateTime.now().format(format);
		}
}

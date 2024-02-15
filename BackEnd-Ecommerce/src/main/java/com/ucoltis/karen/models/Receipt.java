package com.ucoltis.karen.models;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="receipt")
@Data //getters and setters
@AllArgsConstructor //Contructor con datos
@NoArgsConstructor  //Contructor sin datos
public class Receipt {
	
	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	@Column(name="receipt_number")
	private Integer receiptNumber;
	
	//persistencia de datos 
	//orm -- jpa -- realizar las relaciones entre tablas desde java / standar
	//Hibernate tool principal -- standar
	
	
	//Relacion de muchos a uno para users
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "created_by")//crea el campo relacionado a user con el nombre createdby
	private User user;

	//Relacion de muchos receipts para muchos products
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(
		name = "items_on_receipt",//crea el campo bidireccion relacionado a producto con el nombre items_on_receipt
		joinColumns = {@JoinColumn(name = "receiptNumber")},
		inverseJoinColumns = {@JoinColumn(name="itemId")}//Este es un campo que se va a generar		
	)
	private List<Product> products;
	
	private Double total;
	
	@Column(name = "create_data_time")
	private String dateTime;
	
	private Integer amountOfItems;

	public Receipt(User user, List<Product> products, Double total, String dateTime, Integer amountOfItems) {
		super();
		this.user = user;
		this.products = products;
		this.total = total;
		this.dateTime = dateTime;
		this.amountOfItems = amountOfItems;
	}



	
}

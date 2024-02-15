package com.ucoltis.karen.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name="users")
//@Data //getters and setters
//@AllArgsConstructor //Contructor con datos
//@NoArgsConstructor  //Contructor sin datos
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	@Column(name="user_id")
	private Integer userId;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	//Agregando restricción a la base de datos
	
	@Column(unique = true)//Es un valor unico
	private String email;
	
	
	@NotEmpty //No vacio
	@Column
	private String password;
	
	private String addres;
	
	private String phoneNumber;
	
	    //Lista de receipt -- tabla receipt
		//Relacion de uno a muchos desde receipt hacia users
		@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
		@JsonIgnore
		private List<Receipt> userReceips;

	public User() {
		super();
	}

	public User(Integer userId, String firstName, String lastName, String email, @NotEmpty String password,
			String addres, String phoneNumber) {
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.addres = addres;
		this.phoneNumber = phoneNumber;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddres() {
		return addres;
	}

	public void setAddres(String addres) {
		this.addres = addres;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	

	
	
	
	
}

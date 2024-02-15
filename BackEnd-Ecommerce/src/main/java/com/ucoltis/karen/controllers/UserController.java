package com.ucoltis.karen.controllers;

import java.util.LinkedHashMap;

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

import com.ucoltis.karen.models.User;
import com.ucoltis.karen.services.UserService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("users")
@CrossOrigin("*")
@AllArgsConstructor
public class UserController {
	@Autowired
	private UserService userService;
	
	@PostMapping("/register")
	public User register(@RequestBody LinkedHashMap<String, String> body) {
		String firstName = body.get("firstName");
		String lastName = body.get("lastName");
		String email= body.get("email");
		String password = body.get("password");
		String address=body.get("adress");
		String phoneNumber=body.get("phoneNumber");
		
		
		return userService.registerUser(firstName, lastName, email, password, address, phoneNumber);
	}
	//get
	@GetMapping("/read")
	public User readUser(@RequestParam(name="id") Integer id) {
		return userService.readUser(id);
	}
	@PutMapping("/update")
	public User update(@RequestBody User newUser) {
		return userService.updateUser(newUser);
	}
	@DeleteMapping("/delete")
	public String delete(@RequestBody LinkedHashMap<String, Integer> body) {
		return userService.deleteUser(body.get("id"));
	}
	
	@PostMapping("/login")
	public User login(@RequestBody LinkedHashMap<String, String> body) {
		String email = body.get("email");
		String password=body.get("password");
		return userService.loginUser(email, password);
	}
}

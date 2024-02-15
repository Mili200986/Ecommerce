package com.ucoltis.karen.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ucoltis.karen.exceptions.ICException;
import com.ucoltis.karen.exceptions.UException;
import com.ucoltis.karen.models.User;
import com.ucoltis.karen.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {

	@Autowired
	UserRepository userRepository;
	
	//Crear usuario nuevo
	public User registerUser(String firstName, String lastName, String email, String password, String addres, String phoneNumber) {
		User newUser = new User(0, firstName, lastName, email, password, addres, phoneNumber);
		try {
			return userRepository.save(newUser);
		} catch (Exception e) {
			throw new UException();//Creamos nuestro propio manejo de errores
		}		
	}//Fin de userRegister
	
	//metodo para login de usuario
		public User loginUser(String email, String password) {
			User userToLogin = userRepository.getByEmail(email).orElseThrow(ICException:: new); //programacion funcional y lambas en java
			if(!userToLogin.getPassword().equals(password)) {
				throw new ICException();
			}
			return userToLogin;
		}
		
		//leer el usario
		public User readUser(Integer id) {
			return userRepository.findById(id).get();
		}
		//actualizar el usuario
		public User updateUser(User updateUser) {
			User itemUser = userRepository.findById(updateUser.getUserId()).get();
			
			itemUser.setFirstName(updateUser.getFirstName());
			itemUser.setLastName(updateUser.getLastName());
			itemUser.setEmail(updateUser.getEmail());
			itemUser.setPassword(updateUser.getPassword());
			itemUser.setPhoneNumber(updateUser.getPhoneNumber());
			itemUser.setAddres(updateUser.getAddres());
			
			return userRepository.save(itemUser);
		}
		//delete user
		public String deleteUser(Integer id) {
			User userDelete = userRepository.findById(id).get();
			userRepository.delete(userDelete);
			return "Usuario fue eliminado";
		}
		
}


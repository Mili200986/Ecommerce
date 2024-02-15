package com.ucoltis.karen.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ucoltis.karen.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	//agregar un metodo creado por el usuario, busqueda por email
		Optional<User> getByEmail(String email);

}

package com.inacif.rekognition.web.app.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.inacif.rekognition.web.app.Utils;
import com.inacif.rekognition.web.app.entity.Role;
import com.inacif.rekognition.web.app.entity.Status;
import com.inacif.rekognition.web.app.entity.User;
import com.inacif.rekognition.web.app.maps.UserCreate;
import com.inacif.rekognition.web.app.responses.Response;
import com.inacif.rekognition.web.app.service.QueryService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private QueryService queryService;
	
	@GetMapping
	public ResponseEntity<?> getAllUsers(@RequestParam(value="userId", required = false) Long userId){
		
		if(userId != null) {
			Optional<User> user = queryService.getUserById(userId);
			if(user.isEmpty()) {
				return new Response(HttpStatus.NOT_FOUND, "Usuario no encontrado").message();
			}
			return new Response(HttpStatus.OK, "Usuario encontrado", user).message();
		}
		List<User> users = queryService.getAllUsers();
		if(users.isEmpty()) {
			return new Response(HttpStatus.OK, "Usuarios no encontrados").message();
		}
		return new Response(HttpStatus.OK, "Usuarios encontrados", users).message();
	}
	
	@PostMapping
	public ResponseEntity<?> saveUser(@RequestBody UserCreate user){
		User newUser = new User();
		newUser.setUsername(user.getUsername());
		newUser.setNames(user.getName());
		newUser.setLastNames(user.getLastNames());
		newUser.setEmail(user.getEmail());
		newUser.setPhone(user.getPhone());
		newUser.setPassword("123");
		newUser.setCreationDate(Utils.getTimeZoneWithFormat("yyy-MM-dd HH:mm:ss"));
		
		Status userStatus = queryService.getStatusByName("Activo").orElse(null);
		Role userRole = queryService.getRoleById(user.getRole()).get();
		
		newUser.setStatus(userStatus);
		newUser.setRol(userRole);
		queryService.saveUser(newUser);
		return new Response(HttpStatus.OK, "Usuario creado").message();
	}
	
}

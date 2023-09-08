package com.inacif.rekognition.web.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inacif.rekognition.web.app.entity.User;
import com.inacif.rekognition.web.app.maps.Security;
import com.inacif.rekognition.web.app.responses.Response;
import com.inacif.rekognition.web.app.service.QueryService;

@RestController
@RequestMapping("/security")
public class SecurityController {

	@Autowired
	private QueryService queryService;
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody Security security){
		List<User> user = queryService.getUserByUsernameAndPassword(security.getUsername(), security.getPassword());
		if(user.isEmpty()) {
			return new Response(HttpStatus.NOT_FOUND, "Usuario y/o contraseña inválida").message();
		}
		
		return new Response(HttpStatus.OK, "Credenciales correcta", user.get(0)).message();
	}
	
}

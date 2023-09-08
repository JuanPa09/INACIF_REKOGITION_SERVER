package com.inacif.rekognition.web.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inacif.rekognition.web.app.entity.Functionality;
import com.inacif.rekognition.web.app.responses.Response;
import com.inacif.rekognition.web.app.service.QueryService;

@RestController
@RequestMapping("/functionality")
public class FunctionalityController {

	@Autowired
	private QueryService queryService;
	
	@GetMapping
	public ResponseEntity<?> getFunctionalities(){
		List<Functionality> functionalities = queryService.getAllFunctionalities();				
		return new Response(HttpStatus.OK, "Funcionalidades encontradas", functionalities).message();
	}
	
}

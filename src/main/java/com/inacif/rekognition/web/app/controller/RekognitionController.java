package com.inacif.rekognition.web.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.inacif.rekognition.web.app.Constants;
import com.inacif.rekognition.web.app.entity.Rekognition;
import com.inacif.rekognition.web.app.service.FaceComparisonService;

@RestController
@RequestMapping("/rekognition")
public class RekognitionController {
	
	@Autowired
	private FaceComparisonService faceComparisonService;
		
	@GetMapping("/compare")
	public ResponseEntity<?> compare(@RequestParam(value="name") String imageName) {
		Rekognition results = faceComparisonService.compare(Constants.bucketName, imageName, 0);
		return ResponseEntity.status(HttpStatus.CREATED).body(results);
	} 

}

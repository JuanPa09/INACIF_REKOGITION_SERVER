package com.inacif.rekognition.web.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inacif.rekognition.web.app.entity.Settings;
import com.inacif.rekognition.web.app.responses.Response;
import com.inacif.rekognition.web.app.service.QueryService;

@RestController
@RequestMapping("/settings")
public class SettingsController {

	@Autowired
	private QueryService queryService;
	
	@PostMapping
	public ResponseEntity<?> create(@RequestBody Settings settingsEntity){
		Settings settings = queryService.saveSettings(settingsEntity);
		Response response = new Response(HttpStatus.OK, "Ajustes guardados correctamente");
		response.setData(settings);
		return response.message();
	}
	
	@GetMapping
	public ResponseEntity<?> get(){
		List<Settings> settings = queryService.getSettings();
		if(settings.isEmpty()) {
			return new Response(HttpStatus.NOT_FOUND, "No se encontraton configuraciones").message();
		}
		return new Response(HttpStatus.OK, "Ajustes encontrados", settings).message();
	}
	
}

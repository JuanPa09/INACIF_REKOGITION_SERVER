package com.inacif.rekognition.web.app.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.inacif.rekognition.web.app.entity.Role;
import com.inacif.rekognition.web.app.responses.Response;
import com.inacif.rekognition.web.app.service.MenuService;
import com.inacif.rekognition.web.app.service.QueryService;

@RestController
@RequestMapping("/role")
public class RoleController {
	
	@Autowired
	private QueryService queryService;
	
	@Autowired
	private MenuService menuService;
	
	
	@GetMapping
	public ResponseEntity<?> getRoles(@RequestParam(value = "roleId", required = false) Long roleId){
		
		if(roleId != null) {
			Optional<Role> optionalRole = queryService.getRoleById(roleId);
			if(optionalRole.isEmpty()) {
				return new Response(HttpStatus.NOT_FOUND, "Rol no encontrado").message();
			}
			Response response = new Response(HttpStatus.OK, "Rol Encontrado");
			response.setData(optionalRole.get());
			return response.message();
		}
		
		List<Role> roles = queryService.getAllRoles();
		
		if(roles.isEmpty()) {
			return new Response(HttpStatus.NOT_FOUND, "No existen roles").message();
		}
		
		return new Response(HttpStatus.OK, "Rol Encontrado", roles).message();
	}
	
	@PostMapping
	public ResponseEntity<?> createRole(@RequestBody Role roleEntity){
		Role role = queryService.saveRole(roleEntity);
		return new Response(HttpStatus.OK, "Rol creado con éxito", role).message();
	}
	
	@PutMapping
	public ResponseEntity<?> updateRole(@RequestBody Role roleEntity){
		Role role = queryService.saveRole(roleEntity);
		return new Response(HttpStatus.OK, "Rol actualizado con éxito", role).message();
	}
	
	@GetMapping("/menu")
	public ResponseEntity<?> getRoleMenu(@RequestParam(value="roleId") String roleId){
		Object menu = menuService.MapMenu(roleId);
		if(menu == null ) {
			return new Response(HttpStatus.NOT_FOUND, "No se pudo encontrar el rol").message();
		}
		return new Response(HttpStatus.OK, "Menu encontrado exitosamente", menu).message();
	}
	
}

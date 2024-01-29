package com.inacif.rekognition.web.app.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.inacif.rekognition.web.app.entity.Role;
import com.inacif.rekognition.web.app.entity.RoleFunctionality;
import com.inacif.rekognition.web.app.entity.RoleFunctionalityId;
import com.inacif.rekognition.web.app.maps.RoleCreate;
import com.inacif.rekognition.web.app.projection.RolesFunctionalities;
import com.inacif.rekognition.web.app.responses.FunctionalityRoleResultResponse;
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
	public ResponseEntity<?> getRoles(@RequestParam(value = "roleId", required = false ) Long roleId,
									@RequestParam(value = "roleName", required = false ) List<String> roleName){
		
		List<FunctionalityRoleResultResponse> requestResponse = new ArrayList<>();
		if(roleId != null) {
			Optional<Role> optionalRole = queryService.getRoleById(roleId);
			if(optionalRole.isEmpty()) {
				return new Response(HttpStatus.NOT_FOUND, "Rol no encontrado").message();
			}
			List<RolesFunctionalities> functionalities = queryService.getFunctionalitiesByRoleId(roleId);
			requestResponse.add(new FunctionalityRoleResultResponse(optionalRole.get(), functionalities));
			Response response = new Response(HttpStatus.OK, "Rol Encontrado", requestResponse.get(0));
			// response.setData(optionalRole.get());
			return response.message();
		}
		
		if(roleName != null) {
			Optional<List<Long>> optionalRole = queryService.getRoleIdsByNames(roleName);
			if(optionalRole.isEmpty()) {
				return new Response(HttpStatus.NOT_FOUND, "Rol no encontrado").message();
			}
			Response response = new Response(HttpStatus.OK, "Rol Encontrado", optionalRole.get());
			// response.setData(optionalRole.get());
			return response.message();
		}
		
		List<Role> roles = queryService.getAllRoles();
		if(roles.isEmpty()) {
			return new Response(HttpStatus.NOT_FOUND, "No existen roles").message();
		}
		for(Role role: roles) {
			List<RolesFunctionalities> functionalities = queryService.getFunctionalitiesByRoleId(role.getId());
			requestResponse.add(new FunctionalityRoleResultResponse(role, functionalities));
		}
		
		return new Response(HttpStatus.OK, "Rol Encontrado", requestResponse).message();
	}
	
	@PostMapping
	public ResponseEntity<?> createRole(@RequestBody RoleCreate roleCreate){
		Role role = new Role();
		role.setDescription(roleCreate.getDescription());
		role.setName(roleCreate.getName());
		role.setStatus(queryService.getStatusByName("Activo").get());
		role.setUserModifies(roleCreate.getUserModifies());
		Role roleCreated = queryService.saveRole(role);
		
		for(Integer id: roleCreate.getFunctionalities()) {
			RoleFunctionality roleFunctionality = new RoleFunctionality();
			roleFunctionality.id = new RoleFunctionalityId();
			roleFunctionality.getId().setRolId(roleCreated.getId());
			roleFunctionality.getId().setFunctionalityId(id.longValue());
			roleFunctionality.setUserModifies(roleCreate.getUserModifies());
			queryService.saveRoleFunctionality(roleFunctionality);
		}
		
		
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
	
	@DeleteMapping
	public ResponseEntity<?> deleteRoleById(@RequestParam(value = "roleId", required = true) Long roleId){
		
		queryService.deleteRoleById(roleId);
		return new Response(HttpStatus.OK, "Rol Eliminado correctamennte").message();
	}
	
}

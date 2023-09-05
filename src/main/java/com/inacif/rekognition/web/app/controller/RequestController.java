package com.inacif.rekognition.web.app.controller;

import java.util.List;
import java.util.Map;
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

import com.inacif.rekognition.web.app.entity.Request;
import com.inacif.rekognition.web.app.entity.RequestStatus;
import com.inacif.rekognition.web.app.maps.RequestDetail;
import com.inacif.rekognition.web.app.projection.Requests;
import com.inacif.rekognition.web.app.service.QueryService;
import com.inacif.rekognition.web.app.service.RequestService;
import com.inacif.rekognition.web.app.responses.Response;

@RestController
@RequestMapping("/request")
public class RequestController {
	
	@Autowired
	private QueryService queryService;
	
	@Autowired
	private RequestService requestService;
	
	@PostMapping
	public ResponseEntity<?> create(@RequestBody Request requestEntity) {
		Optional<RequestStatus> requestStatus = queryService.getRequestStatusIdByName("Ingresada");
		requestEntity.setStatus(requestStatus.get().getId());
		Request request = queryService.saveRequest(requestEntity);
		Response response = new Response(HttpStatus.OK, "Solicitud cargada correctamente");
		response.setData(request);
		return response.message();
	}
	
	@GetMapping("/detail")
	public ResponseEntity<?> getDetail(@RequestParam(value = "requestId") Long requestId){
		
		RequestDetail requestDetail = requestService.getRequestDetail(requestId);
		if(requestDetail== null) {
			Response response = new Response(HttpStatus.NOT_FOUND, "Solicitud no encontrada");
			return response.message();
		}
		
		Response response = new Response(HttpStatus.OK, "Solicitud encontrada");
		response.setData(requestDetail);
		return response.message();
	}
	
	@GetMapping
	public ResponseEntity<?> getAllRequests(@RequestParam(value = "statusName", required = false) String statusName){
		
		List<Map<String, Object>> orderedRequests;
		Optional<List<Requests>> requests;
		if(statusName != null) {
			Optional<RequestStatus> requestStatus = queryService.getRequestStatusIdByName(statusName);
			
			if(requestStatus.isEmpty()) {
				return new Response(HttpStatus.NOT_FOUND, "Estado de la solicitud no encontrado").message();
			}
			
			Integer currentRequestStatusId = requestStatus.get().getId().intValue();
			
			requests = queryService.getRequestsByStatus(currentRequestStatusId);
			
			if(requests.isEmpty() || requests.get().size() == 0) {
				return new Response(HttpStatus.NOT_FOUND, "Solicitudes con estado " + statusName + " no encontradas").message();
			}
			
			/*List<Map<String, Object>> orderedRequests = requestService.mapAllRequests(requests.get());
			
			Response response = new Response(HttpStatus.OK, "Solicitudes encontradas");
			response.setData(orderedRequests);
			return response.message();*/
		}else {
			requests = queryService.getAllRequests();
			
			if(requests.isEmpty() || requests.get().size() == 0) {
				return new Response(HttpStatus.NOT_FOUND, "Solicitudes no encontradas").message();
			}
		}
		// -----------
		
		
		orderedRequests = requestService.mapAllRequests(requests.get());
		
		Response response = new Response(HttpStatus.OK, "Solicitudes encontradas");
		response.setData(orderedRequests);
		return response.message();
	}
	
	/*@GetMapping("/status")
	public ResponseEntity<?> getRequestsByStatus(@RequestParam(value = "statusName") String statusName){
		
		Optional<RequestStatus> requestStatus = queryService.getRequestStatusIdByName(statusName);
		
		if(requestStatus.isEmpty()) {
			return new Response(HttpStatus.NOT_FOUND, "Estado de la solicitud no encontrado").message();
		}
		
		Integer currentRequestStatusId = requestStatus.get().getId();
		
		Optional<List<Requests>> requests = queryService.getRequestsByStatus(0);
		
		if(requests.isEmpty()) {
			return new Response(HttpStatus.NOT_FOUND, "Solicitudes con estado " + statusName + " no encontradas").message();
		}
		
		List<Map<String, Object>> orderedRequests = requestService.mapAllRequests(requests.get());
		
		Response response = new Response(HttpStatus.OK, "Solicitudes encontradas");
		response.setData(orderedRequests);
		return response.message();
	}*/
	
	@PutMapping("/confirm")
	public ResponseEntity<?> confirmRequest(@RequestParam(value = "requestId") Long requestId){
		Optional<Request> request = queryService.getRequestById(requestId);
		if(request.isEmpty()) {
			return new Response(HttpStatus.NOT_FOUND, "Solicitud no encontrada").message();
		}
		Request currentRequest = request.get();
		requestService.updateRequestStatus(currentRequest, "Confirmada");
		return new Response(HttpStatus.OK, "Solicitud confirmada con éxito").message();
	}
	
	@PutMapping("/pending")
	public ResponseEntity<?> pendingRequest(@RequestParam(value = "requestId") Long requestId){
		Optional<Request> request = queryService.getRequestById(requestId);
		if(request.isEmpty()) {
			return new Response(HttpStatus.NOT_FOUND, "Solicitud no encontrada").message();
		}
		Request currentRequest = request.get();
		requestService.updateRequestStatus(currentRequest, "Pendiente");
		return new Response(HttpStatus.OK, "Solicitud confirmada con éxito").message();
	}
	
	@PutMapping("/reject")
	public ResponseEntity<?> rejectRequest(@RequestParam(value = "requestId") Long requestId){
		Optional<Request> request = queryService.getRequestById(requestId);
		if(request.isEmpty()) {
			return new Response(HttpStatus.NOT_FOUND, "Solicitud no encontrada").message();
		}
		Request currentRequest = request.get();
		requestService.updateRequestStatus(currentRequest, "Rechazada");
		return new Response(HttpStatus.OK, "Solicitud confirmada con éxito").message();
	}
	
	@PutMapping("/resolve")
	public ResponseEntity<?> resolveRequest(@RequestParam(value = "requestId") Long requestId){
		Optional<Request> request = queryService.getRequestById(requestId);
		if(request.isEmpty()) {
			return new Response(HttpStatus.NOT_FOUND, "Solicitud no encontrada").message();
		}
		Request currentRequest = request.get();
		requestService.updateRequestStatus(currentRequest, "Resuelta");
		return new Response(HttpStatus.OK, "Solicitud confirmada con éxito").message();
	}
	
	@PutMapping("/validate")
	public ResponseEntity<?> validateRequest(@RequestParam(value = "requestId") Long requestId){
		Optional<Request> request = queryService.getRequestById(requestId);
		if(request.isEmpty()) {
			return new Response(HttpStatus.NOT_FOUND, "Solicitud no encontrada").message();
		}
		Request currentRequest = request.get();
		requestService.updateRequestStatus(currentRequest, "Validada");
		return new Response(HttpStatus.OK, "Solicitud confirmada con éxito").message();
	}
	
	

}

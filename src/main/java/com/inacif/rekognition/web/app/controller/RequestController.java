package com.inacif.rekognition.web.app.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

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

import com.inacif.rekognition.web.app.Constants;
import com.inacif.rekognition.web.app.Utils;
import com.inacif.rekognition.web.app.entity.ConfirmationCode;
import com.inacif.rekognition.web.app.entity.Request;
import com.inacif.rekognition.web.app.entity.RequestStatus;
import com.inacif.rekognition.web.app.maps.RequestDetail;
import com.inacif.rekognition.web.app.projection.Requests;
import com.inacif.rekognition.web.app.responses.Response;
import com.inacif.rekognition.web.app.service.EmailService;
import com.inacif.rekognition.web.app.service.QueryService;
import com.inacif.rekognition.web.app.service.RequestService;

@RestController
@RequestMapping("/request")
public class RequestController {
	
	@Autowired
	private QueryService queryService;
	
	@Autowired
	private RequestService requestService;
	
	@Autowired
	private EmailService emailService;
	
	@PostMapping
	public ResponseEntity<?> create(@RequestBody Request requestEntity) {
		
		Optional<RequestStatus> requestStatus = queryService.getRequestStatusIdByName("Ingresada");
		requestEntity.setStatus(requestStatus.get().getId());
		Request request = queryService.saveRequest(requestEntity);
		
		ConfirmationCode confirmationCodeEntity = new ConfirmationCode();
		confirmationCodeEntity.setChannel("email");
		String code = UUID.randomUUID().toString();
		confirmationCodeEntity.setCode(code);
		confirmationCodeEntity.setExpirationTimestamp(Utils.getExpirationTimeZone(7).toString());
		confirmationCodeEntity.setRequest(request);
		queryService.saveConfirmationCode(confirmationCodeEntity);
		
		String url = Constants.domain + "/email/confirmed?code=" + code;
		String body = Constants.emailConfirmationTemplate;
		body = body.replace("{applicantName}", request.getApplicantNames() + " " + request.getApplicantLastNames());
		body = body.replace("{confirmationUrl}", url);
		emailService.sendEmail(request.getApplicantEmail(), "Solicitud por desaparición de personas", body);
		
		Response response = new Response(HttpStatus.OK, "Solicitud cargada correctamente");
		//response.setData(request);
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
	
	@PutMapping("/emailConfirmation")
	public ResponseEntity<?> requestEmailConfirmation(@RequestParam(value = "code") String uuid){
		Long requestNumber = requestService.emailConfirmation(uuid);
		if(requestNumber == null) {
			return new Response(HttpStatus.OK, "Solicitud no encontrada").message();
		}
		return new Response(HttpStatus.OK, "Correo confirmado", requestNumber).message();
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

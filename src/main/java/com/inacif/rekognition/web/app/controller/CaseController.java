package com.inacif.rekognition.web.app.controller;



import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inacif.rekognition.web.app.Constants;
import com.inacif.rekognition.web.app.entity.CaseInfo;
import com.inacif.rekognition.web.app.responses.Response;
import com.inacif.rekognition.web.app.service.CaseService;
import com.inacif.rekognition.web.app.service.EmailService;




@RestController
@RequestMapping("/case")
public class CaseController {
	
	
	@Autowired
	private CaseService caseService;
	
	@Autowired
	private EmailService emailService;
	
	
	@PostMapping
	public ResponseEntity<?> create(@RequestBody CaseInfo caseEntity) {
		return ResponseEntity.status(HttpStatus.CREATED).body(caseService.save(caseEntity));
	}
	
	@GetMapping
	public ResponseEntity<?> read(@PathVariable(value = "id") Long caseId) {
		Optional<CaseInfo> oCase = caseService.findById(caseId);
		
		if(!oCase.isPresent()) {
			return ResponseEntity.notFound().build();
		}else {
			return ResponseEntity.ok(oCase);
		}
	}
	
	@PutMapping
	public ResponseEntity<?> update(@RequestBody CaseInfo caseEntity) {
        Optional<CaseInfo> caseExisting = caseService.findById(caseEntity.getId());
        if(!caseExisting.isPresent()) {
        	 Response response = new Response(HttpStatus.NOT_FOUND,"Caso no encontrado");
             return response.message();
		}
        caseService.save(caseEntity);
        Response response = new Response(HttpStatus.OK,"Caso actualizado correctamente");
        return response.message();
    }
	
	@GetMapping("/mail")
	public void mail() {
		String body = Constants.requestConfirmationTemplateString;
		body = body.replace(":requestId", "100022");
		body = body.replace(":applicant", "Karina");
		body = body.replace(":address", "14 calle 7-05");
		body = body.replace(":phone", "30237820");
		emailService.sendEmail("juanpa.a.l10@gmail.com", "Prueba", body);
	}

}

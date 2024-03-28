package com.inacif.rekognition.web.app.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.inacif.rekognition.web.app.Constants;
import com.inacif.rekognition.web.app.Utils;
import com.inacif.rekognition.web.app.entity.CaseInfo;
import com.inacif.rekognition.web.app.entity.Rekognition;
import com.inacif.rekognition.web.app.entity.RekognitionInfo;
import com.inacif.rekognition.web.app.entity.Request;
import com.inacif.rekognition.web.app.entity.RequestStatus;
import com.inacif.rekognition.web.app.entity.Settings;
import com.inacif.rekognition.web.app.maps.ComparisonResponse;
import com.inacif.rekognition.web.app.maps.ComparisonResult;
import com.inacif.rekognition.web.app.maps.ImagesUrl;
import com.inacif.rekognition.web.app.responses.OtherComparisonResult;
import com.inacif.rekognition.web.app.responses.Response;
import com.inacif.rekognition.web.app.service.DataProcessorService;
import com.inacif.rekognition.web.app.service.FaceComparisonService;
import com.inacif.rekognition.web.app.service.QueryService;
import com.inacif.rekognition.web.app.service.S3ImageService;

@RestController
@RequestMapping("/compare")
public class ComparisonController {

	@Autowired
	private QueryService queryService;
	@Autowired
	private FaceComparisonService faceComparisonService;
	@Autowired
	private DataProcessorService dataProcessorService;
	@Autowired
	private S3ImageService s3ImageService;
	
	@GetMapping
	public ResponseEntity<?> getComparisonInfoRecords(@RequestParam(value="requestId") Long requestId, @RequestParam(required = false, value="caseId") String caseId){
		
		System.out.println("Obteniendo solicitud..");
		Optional<Request> optionalRequest = queryService.getRequestById(requestId);
		if(optionalRequest.isEmpty()) {
			Response response = new Response(HttpStatus.NOT_FOUND, "No se encontró la solicitud");
			return response.message();
		}
		Request currentRequest = optionalRequest.get();
		
		System.out.println("Obteniendo configuraciones");
		Settings settings = Utils.getDefaultSettings();
		List<Settings> settingsList = queryService.getSettings();
		if(!settingsList.isEmpty()) {
			settings = settingsList.get(0);
		}
		
		System.out.println("Obteniendo resultados de rekognition");
		long startTime = System.currentTimeMillis();
		Rekognition results = faceComparisonService.compare(Constants.bucketName, currentRequest.getImage(), settings.getImage());
		long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;
        System.out.println("Tiempo de ejecución: " + elapsedTime + " milisegundos");
		if(Utils.isEmpty(results.getData())) {
			Response response = new Response(HttpStatus.NOT_FOUND, "No se encontraron coincidencias");
			return response.message();
		}
		List<String> imagesNames = new ArrayList<>(); ;
		for(RekognitionInfo comparisonResult : results.getData()) {
			imagesNames.add(comparisonResult.getImageName());
		};
		
		System.out.println("Obteniendo datos de los casos");
		System.out.println(imagesNames);
		List<CaseInfo> casesInfoResults = queryService.getCasesInfoByImagesNames(imagesNames);
		if(Utils.isEmpty(casesInfoResults) || casesInfoResults.isEmpty()) {
			Response response = new Response(HttpStatus.NOT_FOUND, "No se encontraron casos relacionados");
			return response.message();
		}
		
		System.out.println("Obteniendo el caso solicitado");
		CaseInfo currentCase = null;
		List<CaseInfo> filteredCases = new ArrayList<>();;
		if(caseId!=null) {
			for (CaseInfo caseInfo: casesInfoResults){
				if(caseInfo.getId().toString().equals(caseId.toString())) {
					currentCase = caseInfo;
					break;
				}
			}
			if(currentCase == null) {
				Response response = new Response(HttpStatus.NOT_FOUND, "Caso no encontrado");
				return response.message();
			}
		}else {
			
			//Filtering cases with filter settings
			for(CaseInfo caseInfo: casesInfoResults) {
				if(dataProcessorService.isOnFilters(settings, dataProcessorService.getComparisonPercentages(currentRequest, caseInfo))) {
					filteredCases.add(caseInfo);
				}
			}
			
			if(filteredCases.isEmpty()) {
				Response response = new Response(HttpStatus.NOT_FOUND, "Casos no cumplen con filtros");
				return response.message();
			}
			currentCase = filteredCases.get(0);
		}
		
		List<CaseInfo> casesList = casesInfoResults;
		if(!filteredCases.isEmpty()) {
			casesList = filteredCases;
		}
		
		// Filtering list with the other results
		List<OtherComparisonResult> otherResults = new ArrayList<>();
		for(CaseInfo caseInfo: casesList) {
			if(caseInfo.getId() != currentCase.getId()) {
				otherResults.add(new OtherComparisonResult(caseInfo));
			}
		}
		
		System.out.println("Obteniendo porcentajes de comparación");
		ComparisonResult comparisonResult = dataProcessorService.getComparisonPercentages(currentRequest, currentCase);
		for(RekognitionInfo cprsts : results.getData()) {
			if(cprsts.getImageName().equals(currentCase.getImage())) {
				comparisonResult.setImage(cprsts.getSimilarity());
				break;
			}
		};
		//comparisonResult.setImage(results.getData().get(0).getSimilarity());
		
		
		
		
		
		System.out.println("Obteniendo url firmada de s3");
		ImagesUrl imagesUrl = new ImagesUrl(s3ImageService.getSignedUrl(currentRequest.getImage()), s3ImageService.getSignedUrl(currentCase.getImage()));
		
		ComparisonResponse comparisonResponse = new ComparisonResponse(currentRequest, comparisonResult, currentCase, imagesUrl, otherResults);
		Optional<RequestStatus> requestStatus= queryService.getRequestStatusById(currentRequest.getStatusId());
		comparisonResponse.setStatus(requestStatus.get().getName());
		
		Response response = new Response(HttpStatus.OK, "Resultados encontrados");
		response.setData(comparisonResponse);
		return response.message();
	}
	
}

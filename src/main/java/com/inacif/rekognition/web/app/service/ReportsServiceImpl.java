package com.inacif.rekognition.web.app.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inacif.rekognition.web.app.projection.CaseReport;
import com.inacif.rekognition.web.app.projection.RequestsReport1;
import com.inacif.rekognition.web.app.projection.RequestsReport2;
import com.inacif.rekognition.web.app.repository.CaseRepository;
import com.inacif.rekognition.web.app.repository.RequestRepository;

@Service
public class ReportsServiceImpl implements ReportsService {

	@Autowired RequestRepository reportsRepository;
	@Autowired CaseRepository caseRepository;
	
	@Override
	public Optional<List<RequestsReport1>> getRequestsReport(String initDate, String endDate, String condition){
		if(condition.equals("Todas"))
			return reportsRepository.getAllRequestsByDate(initDate, endDate);
		return reportsRepository.getRequestsByDate(initDate, endDate, condition);
	}
	@Override
	public Optional<List<CaseReport>> getCasesByDates(String initDate, String endDate) {
		return caseRepository.findByCreatedDateBetween(initDate, endDate); 
	}
	@Override
	public Optional<List<RequestsReport2>> getRequestsForDay(String initDate, String endDate, String condition) {
		if(condition.equals("Todas"))
			return reportsRepository.getAllRequestsForDay(initDate, endDate);
		return reportsRepository.getRequestsForDay(initDate, endDate, condition);
	}
	@Override
	public List<Map<String, Object>> mapReports(List<?> requests, int reportId, String condition) {
		
		List<Map<String, Object>> orderedRequests = new ArrayList<>();
		switch (reportId) {
			case 0: {
				@SuppressWarnings("unchecked")
				List<RequestsReport1> requests1 = (List<RequestsReport1>) requests;
				requests1.sort(Comparator.comparing(RequestsReport1::getId));
		        for (RequestsReport1 request : requests1) {
		            Map<String, Object> orderedMap = new LinkedHashMap<>();
		            orderedMap.put("Número de solicitud", request.getId());
		            orderedMap.put("Nombres solicitante", request.getApplicantNames());
		            orderedMap.put("Apellidos solicitante", request.getApplicantLastNames());
		            orderedMap.put("Correo solicitante", request.getApplicantEmail());
		            orderedMap.put("Teléfono solicitante", request.getApplicantPhone());
		            orderedMap.put("Fecha de atención", request.getAttentionDate());
		            if(condition.equals("Todas"))
		            	orderedMap.put("Estado", request.getStatus());
		            orderedRequests.add(orderedMap);
		        }
			}break;
			case 1: {
				@SuppressWarnings("unchecked")
				List<RequestsReport2> requests1 = (List<RequestsReport2>) requests;
				requests1.sort(Comparator.comparing(RequestsReport2::getCreatedDate));
		        for (RequestsReport2 request : requests1) {
		            Map<String, Object> orderedMap = new LinkedHashMap<>();
		            orderedMap.put("Fecha de ingreso", request.getCreatedDate());
		            orderedMap.put("Cantidad", request.getCount());
		            orderedRequests.add(orderedMap);
		        }
			}break;
			case 2: {
				@SuppressWarnings("unchecked")
				List<CaseReport> requests1 = (List<CaseReport>) requests;
				requests1.sort(Comparator.comparing(CaseReport::getId));
		        for (CaseReport request : requests1) {
		            Map<String, Object> orderedMap = new LinkedHashMap<>();
		            orderedMap.put("Id", request.getId());
		            orderedMap.put("Nombre de caso", request.getCaseName());
		            orderedMap.put("Fecha de creación", request.getCreatedDate());
		            orderedRequests.add(orderedMap);
		        }
			}break;
		}
		return orderedRequests;
	}
}

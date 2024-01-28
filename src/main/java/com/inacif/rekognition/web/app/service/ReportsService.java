package com.inacif.rekognition.web.app.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.inacif.rekognition.web.app.projection.CaseReport;
import com.inacif.rekognition.web.app.projection.RequestsReport1;
import com.inacif.rekognition.web.app.projection.RequestsReport2;

public interface ReportsService {
	
	Optional<List<RequestsReport1>> getRequestsReport(String initDate, String endDate, String conndition);
	
	Optional<List<CaseReport>> getCasesByDates(String initDate, String endDate);
	
	Optional<List<RequestsReport2>> getRequestsForDay(String initDate, String endDate, String condition);
	
	List<Map<String, Object>> mapReports(List<?> requests, int reportId, String condition);

}

package com.inacif.rekognition.web.app.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity; 
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.inacif.rekognition.web.app.responses.Response;
import com.inacif.rekognition.web.app.maps.RequestsReports;
import com.inacif.rekognition.web.app.service.ReportsService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/reports")
public class ReportsController {
	
	@Autowired ReportsService reportsService;
	@PostMapping
	public ResponseEntity<?> getRequestsReport(@Valid @RequestBody RequestsReports reportBody) {
        Response response = new Response(HttpStatus.OK, "Reporte generado exitosamente");

        Optional<? extends List<?>> result = switch (reportBody.getReportId()) {
            case 0 -> reportsService.getRequestsReport(reportBody.getInitDate(), reportBody.getEndDate(), reportBody.getCondition());
            case 1 -> reportsService.getRequestsForDay(reportBody.getInitDate(), reportBody.getEndDate(), reportBody.getCondition());
            case 2 -> reportsService.getCasesByDates(reportBody.getInitDate(), reportBody.getEndDate());
            default -> Optional.empty();
        };

        if (result.isEmpty()) {
            return new Response(HttpStatus.NOT_FOUND, "Sin resultados").message();
        }
        response.setData( reportsService.mapReports(result.get(), reportBody.getReportId(), reportBody.getCondition()));
        return response.message();
    }
}

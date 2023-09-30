package com.inacif.rekognition.web.app.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.inacif.rekognition.web.app.entity.Request;
import com.inacif.rekognition.web.app.maps.RequestDetail;
import com.inacif.rekognition.web.app.projection.Requests;

public interface RequestService {
	
	RequestDetail getRequestDetail(Long requestId);
	
	Optional<List<Requests>> getAllRequests();
	
	public List<Map<String, Object>> mapAllRequests(List<Requests> requests);
	
	public Request updateRequestStatus(Request request, String status);
	
	public Long emailConfirmation(String code);
	
}

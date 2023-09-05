package com.inacif.rekognition.web.app.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inacif.rekognition.web.app.Utils;
import com.inacif.rekognition.web.app.entity.Request;
import com.inacif.rekognition.web.app.entity.RequestStatus;
import com.inacif.rekognition.web.app.maps.RequestDetail;
import com.inacif.rekognition.web.app.projection.RequestApplicantDetail;
import com.inacif.rekognition.web.app.projection.RequestCitizenDetail;
import com.inacif.rekognition.web.app.projection.Requests;

@Service
public class RequestServiceImpl implements RequestService {
	
	@Autowired
	S3ImageService s3ImageService;
	
	@Autowired
	QueryService queryService;

	@Override
	public RequestDetail getRequestDetail(Long requestId) {
		Optional<RequestCitizenDetail> citizen = queryService.getRequestCitizenDetail(requestId); 
		Optional<RequestApplicantDetail> applicant = queryService.getApplicantDetail(requestId);
		Optional<Request> request =  queryService.getRequestById(requestId);
		
		if(citizen.isEmpty() || applicant.isEmpty()) {
			return null;
		}
		
		Optional<RequestStatus> requestStatus = queryService.getRequestStatusById(request.get().getStatusId());
		RequestDetail requestDetail = new RequestDetail(applicant.get(), citizen.get());
		String imageUrl = s3ImageService.getSignedUrl(request.get().getImage());
		requestDetail.setImage(imageUrl);
		requestDetail.setCaseNumber(requestId);
		requestDetail.setStatus(requestStatus.get().getName());
		return requestDetail;
	}

	@Override
	public Optional<List<Requests>> getAllRequests() {
		return queryService.getAllRequests();
	}

	@Override
	public List<Map<String, Object>> mapAllRequests(List<Requests> requests) {
		requests.sort(Comparator.comparing(Requests::getId));

        List<Map<String, Object>> orderedRequests = new ArrayList<>();
        for (Requests request : requests) {
            Map<String, Object> orderedMap = new LinkedHashMap<>();
            orderedMap.put("id", request.getId());
            orderedMap.put("Número de solicitud", request.getId());
            orderedMap.put("Número de identificación", request.getApplicantIdentificationNumber());
            orderedMap.put("Nombres", request.getApplicantNames());
            orderedMap.put("Apellidos", request.getApplicantLastNames());
            orderedMap.put("Fecha de solicitud", request.getCreatedDate());
            orderedMap.put("Fecha de atención", request.getAttentionDate() == null ? "-" : request.getAttentionDate());
            orderedMap.put("Estado", request.getName());
            orderedRequests.add(orderedMap);
        }
        return orderedRequests;
	}

	@Override
	public Request updateRequestStatus(Request request, String status) {
		request.setAttentionDate(Utils.getTimeZoneWithFormat("YYYY-MM-dd"));
		request.setStatus(queryService.getRequestStatusIdByName(status).get().getId());
		queryService.updateRequest(request);
		return request;
	}

	

	

}

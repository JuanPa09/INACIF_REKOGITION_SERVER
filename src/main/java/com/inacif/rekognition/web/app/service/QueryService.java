package com.inacif.rekognition.web.app.service;

import java.util.List;
import java.util.Optional;

import com.inacif.rekognition.web.app.entity.CaseInfo;
import com.inacif.rekognition.web.app.entity.Request;
import com.inacif.rekognition.web.app.entity.RequestStatus;
import com.inacif.rekognition.web.app.entity.Role;
import com.inacif.rekognition.web.app.entity.SPMenu;
import com.inacif.rekognition.web.app.entity.Settings;
import com.inacif.rekognition.web.app.entity.Status;
import com.inacif.rekognition.web.app.projection.RequestApplicantDetail;
import com.inacif.rekognition.web.app.projection.RequestCitizenDetail;
import com.inacif.rekognition.web.app.projection.Requests;


public interface QueryService {
	
	public List<CaseInfo> getCasesInfoByImagesNames(List<String> imagesNames);
	
	public Optional<RequestStatus> getRequestStatusIdByName(String name);
	
	public Optional<List<Requests>> getRequestsByStatus(Integer status);
	
	public Request getRequestByImage(String name);
	
	public Request saveRequest(Request request);
	
	public Optional<Request> getRequestById(Long id);
	
	public Optional<RequestCitizenDetail> getRequestCitizenDetail(Long id);
	
	public Optional<RequestApplicantDetail> getApplicantDetail(Long id);
	
	public Optional<List<Requests>> getAllRequests();
	
	public Request updateRequest(Request request);
	
	public Optional<RequestStatus> getRequestStatusById(Long id);
	
	public List<Settings> getSettings();
	
	public Settings saveSettings(Settings settings);
	
	public Optional<Status> getStatusByName(String name);
	
	// ROLES
	public Role saveRole(Role role);
	
	public List<Role> getAllRoles();
	
	public Optional<Role> getRoleById(Long id);
	
	// MENU
	public List<SPMenu> callSp_getMenuByRole(String param);
	
	
}

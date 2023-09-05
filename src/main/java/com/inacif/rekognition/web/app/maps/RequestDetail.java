package com.inacif.rekognition.web.app.maps;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.inacif.rekognition.web.app.projection.RequestApplicantDetail;
import com.inacif.rekognition.web.app.projection.RequestCitizenDetail;

public class RequestDetail {

	@JsonProperty("applicant")
    public RequestApplicantDetail applicantDetail;

    @JsonProperty("citizen")
    public RequestCitizenDetail citizenDetail;
    
    @JsonProperty("image")
    public String image;
    
    @JsonProperty("caseNumber")
    public Long caseNumber;
    
    @JsonProperty("status")
    public String status;
    

    public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public RequestDetail(RequestApplicantDetail applicantDetail, RequestCitizenDetail citizenDetail) {
    	this.applicantDetail = applicantDetail;
    	this.citizenDetail = citizenDetail;
    }

	public RequestApplicantDetail getApplicantDetail() {
		return applicantDetail;
	}

	public void setApplicantDetail(RequestApplicantDetail applicantDetail) {
		this.applicantDetail = applicantDetail;
	}

	public RequestCitizenDetail getCitizenDetail() {
		return citizenDetail;
	}

	public void setCitizenDetail(RequestCitizenDetail citizenDetail) {
		this.citizenDetail = citizenDetail;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Long getCaseNumber() {
		return caseNumber;
	}

	public void setCaseNumber(Long caseNumber) {
		this.caseNumber = caseNumber;
	}
}

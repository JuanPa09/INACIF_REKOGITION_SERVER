package com.inacif.rekognition.web.app.maps;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.inacif.rekognition.web.app.entity.CaseInfo;
import com.inacif.rekognition.web.app.entity.Request;
import com.inacif.rekognition.web.app.responses.OtherComparisonResult;

public class ComparisonResponse {

	Long id;
	Applicant applicant;
	Citizen citizen;
	CaseComparison inacif;
	ImagesUrl images;
	List<OtherComparisonResult> otherResults;
	String status;
	
	@JsonProperty("compareResults")
	ComparisonResult comparison;

	
	public ComparisonResponse(Request request, ComparisonResult comparison, CaseInfo caseInfo, ImagesUrl imagesUrl, List<OtherComparisonResult> otherResults) {
		this.applicant = new Applicant(request);
		this.citizen = new Citizen(request);
		this.comparison = comparison;
		this.inacif = new CaseComparison(caseInfo);
		this.images = imagesUrl;
		this.otherResults = otherResults;
		this.id = request.getId();
	}
	
	
	
	public String getStatus() {
		return status;
	}



	public void setStatus(String status) {
		this.status = status;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}



	public List<OtherComparisonResult> getOtherResults() {
		return otherResults;
	}

	public void setOtherResults(List<OtherComparisonResult> otherResults) {
		this.otherResults = otherResults;
	}
	
	public ImagesUrl getImages() {
		return images;
	}

	public void setImages(ImagesUrl images) {
		this.images = images;
	}

	public Applicant getApplicant() {
		return applicant;
	}

	public void setApplicant(Applicant applicant) {
		this.applicant = applicant;
	}

	public Citizen getCitizen() {
		return citizen;
	}

	public void setCitizen(Citizen citizen) {
		this.citizen = citizen;
	}

	public ComparisonResult getComparison() {
		return comparison;
	}

	public void setComparison(ComparisonResult comparison) {
		this.comparison = comparison;
	}
	
	public CaseComparison getInacif() {
		return inacif;
	}

	public void setInacif(CaseComparison inacif) {
		this.inacif = inacif;
	}
	
	
	
}

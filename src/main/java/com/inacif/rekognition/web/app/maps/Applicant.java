package com.inacif.rekognition.web.app.maps;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.inacif.rekognition.web.app.Utils;
import com.inacif.rekognition.web.app.entity.Request;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

public class Applicant {
    
    @JsonProperty("Nacionalidad")
    private String applicantNationality;
    
    @JsonProperty("Documento de identificación")
    private String applicantIdentificationType;
    
    @JsonProperty("No. Identificación")
    private String applicantIdentificationNumber;
    
    @JsonProperty("Fecha de nacimiento")
    private String applicantBirthdate;
    
    @JsonProperty("Nombres")
    private String applicantNames;
    
    @JsonProperty("Apellidos")
    private String applicantLastNames;
    
    @JsonProperty("Sexo")
    private String applicantSex;
    
    @JsonProperty("Teléfono")
    private int applicantPhone;
    
    @JsonProperty("Corre electrónico")
    private String applicantEmail;
    
    public Applicant(Request request) {
    	this.setApplicantBirthdate(request.getApplicantBirthdate());
    	this.setApplicantEmail(request.getApplicantEmail());
    	this.setApplicantIdentificationNumber(request.getApplicantIdentificationNumber());
    	this.setApplicantIdentificationType(request.getApplicantIdentificationType());
    	this.setApplicantLastNames(request.getApplicantLastNames());
    	this.setApplicantNames(request.getApplicantNames());
    	this.setApplicantNationality(request.getApplicantNationality());
    	this.setApplicantPhone(request.getApplicantPhone());
    	this.setApplicantSex(request.getApplicantSex());
    }

	public String getApplicantNationality() {
		return applicantNationality;
	}

	public void setApplicantNationality(String applicantNationality) {
		this.applicantNationality = applicantNationality;
	}

	public String getApplicantIdentificationType() {
		return applicantIdentificationType;
	}

	public void setApplicantIdentificationType(String applicantIdentificationType) {
		this.applicantIdentificationType = applicantIdentificationType;
	}

	public String getApplicantIdentificationNumber() {
		return applicantIdentificationNumber;
	}

	public void setApplicantIdentificationNumber(String applicantIdentificationNumber) {
		this.applicantIdentificationNumber = applicantIdentificationNumber;
	}

	public String getApplicantBirthdate() {
		return Utils.getDateWithFormat(applicantBirthdate);
	}

	public void setApplicantBirthdate(String applicantBirthdate) {
		this.applicantBirthdate = applicantBirthdate;
	}

	public String getApplicantNames() {
		return applicantNames;
	}

	public void setApplicantNames(String applicantNames) {
		this.applicantNames = applicantNames;
	}

	public String getApplicantLastNames() {
		return applicantLastNames;
	}

	public void setApplicantLastNames(String applicantLastNames) {
		this.applicantLastNames = applicantLastNames;
	}

	public String getApplicantSex() {
		return applicantSex;
	}

	public void setApplicantSex(String applicantSex) {
		this.applicantSex = applicantSex;
	}

	public int getApplicantPhone() {
		return applicantPhone;
	}

	public void setApplicantPhone(int applicantPhone) {
		this.applicantPhone = applicantPhone;
	}

	public String getApplicantEmail() {
		return applicantEmail;
	}

	public void setApplicantEmail(String applicantEmail) {
		this.applicantEmail = applicantEmail;
	}
	
}
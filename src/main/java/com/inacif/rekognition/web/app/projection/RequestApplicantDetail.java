package com.inacif.rekognition.web.app.projection;

import com.fasterxml.jackson.annotation.JsonProperty;

public interface RequestApplicantDetail {
	
	@JsonProperty("Nacionalidad")
	String getApplicantNationality();
	
	@JsonProperty("Documento de identificación")
	String getApplicantIdentificationType();
	
	@JsonProperty("No. de identificación")
	String getApplicantIdentificationNumber();
	
	@JsonProperty("Fecha de nacimiento")
	String getApplicantBirthdate();
	
	@JsonProperty("Nombres")
	String getApplicantNames();
	
	@JsonProperty("Apellidos")
	String getApplicantLastNames();
	
	@JsonProperty("Sexo")
	String getApplicantSex();
	
	@JsonProperty("Teléfono")
	String getApplicantPhone();
	
	@JsonProperty("Correo")
	String getApplicantEmail();
}

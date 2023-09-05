package com.inacif.rekognition.web.app.projection;

import com.fasterxml.jackson.annotation.JsonProperty;

public interface Requests {
	
	@JsonProperty("Número de solicitud")
    String getId();
	
	@JsonProperty("Número de identificación")
    String getApplicantIdentificationNumber();
	
	@JsonProperty("Nombres")
    String getApplicantNames();
	
	@JsonProperty("Apellidos")
    String getApplicantLastNames();
	
	@JsonProperty("Fecha de solicitud")
    String getCreatedDate();
	
	@JsonProperty("Fecha de atención")
    String getAttentionDate();
	
	@JsonProperty("Estado")
    String getName();
	
}

package com.inacif.rekognition.web.app.projection;

import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonProperty;

public interface RequestsReport1 {

	@JsonProperty("Número de solicitud")
	String getId();
	
	@JsonProperty("Nombres solicitante")
	String getApplicantNames();
	
	@JsonProperty("Apellidos solicitante")
	String getApplicantLastNames();
	
	@JsonProperty("Teléfono solicitante")
	String getApplicantPhone();
	
	@JsonProperty("Correo solicitante")
	String getApplicantEmail();
	
	@JsonProperty("Fecha de atención")
	String getAttentionDate();
	
	@JsonProperty("Estado")
	Optional<String> getStatus();
	
}

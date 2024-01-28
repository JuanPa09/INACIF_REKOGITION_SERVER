package com.inacif.rekognition.web.app.projection;

import com.fasterxml.jackson.annotation.JsonProperty;

public interface CaseReport {

	@JsonProperty("Número de caso")
	Long getId();
	
	@JsonProperty("Nombre")
	String getCaseName();
	
	@JsonProperty("Fecha de creación")
	String getCreatedDate();
	
}

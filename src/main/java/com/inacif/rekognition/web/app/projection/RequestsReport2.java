package com.inacif.rekognition.web.app.projection;

import com.fasterxml.jackson.annotation.JsonProperty;

public interface RequestsReport2 {
	
	@JsonProperty("Fecha de atención")
	String getCreatedDate();
	
	@JsonProperty("Cantidad")
	Long getCount();
}

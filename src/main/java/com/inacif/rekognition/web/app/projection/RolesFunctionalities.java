package com.inacif.rekognition.web.app.projection;

import com.fasterxml.jackson.annotation.JsonProperty;

public interface RolesFunctionalities {

	@JsonProperty("id")
	String getId();
	
	@JsonProperty("name")
	String getName();
}

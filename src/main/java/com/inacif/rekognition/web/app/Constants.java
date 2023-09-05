package com.inacif.rekognition.web.app;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;

public class Constants {
	
	public static String timeZone = "America/Guatemala";
	public static String bucketName = "inacif-rekognition-bucket";
	
	public static final Map<HttpStatus, String> statusMessages = new HashMap<>();
	static {
		statusMessages.put(HttpStatus.OK, "OPERATION_SUCCESSFUL");
		statusMessages.put(HttpStatus.NOT_FOUND, "NOT_FOUND");
		statusMessages.put(HttpStatus.BAD_REQUEST, "BAD_REQUEST");
		statusMessages.put(HttpStatus.INTERNAL_SERVER_ERROR, "INTERNAL_ERROR");
	}
	
	
	
	public static String getMessage(HttpStatus status) {
        return statusMessages.getOrDefault(status, "INTERNAL_ERROR");
    }
	
}

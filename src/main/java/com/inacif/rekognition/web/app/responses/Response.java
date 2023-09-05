package com.inacif.rekognition.web.app.responses;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.inacif.rekognition.web.app.Constants;
import com.inacif.rekognition.web.app.Utils;

public class Response {

	private String code;
	private String datetime;
	private String message;
	private HttpStatus status;
	private Object data;
	
	public Response(HttpStatus status,String message) {
		this.status = status;
		this.code = Constants.getMessage(status);
		this.setDatetime(Utils.getTimeZone());
		this.setMessage(message);
	}
	
	public Response(HttpStatus status,String message, Object data) {
		this.status = status;
		this.code = Constants.getMessage(status);
		this.setDatetime(Utils.getTimeZone());
		this.setMessage(message);
		this.data = data;
	}

	public String getDatetime() {
		return datetime;
	}

	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ResponseEntity<?> message(){
		return ResponseEntity.status(this.status).body(this);
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
	
}

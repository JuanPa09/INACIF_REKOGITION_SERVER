package com.inacif.rekognition.web.app.persistance;

import com.inacif.rekognition.web.app.entity.Request;

import jakarta.persistence.PrePersist;

import java.text.SimpleDateFormat;
import java.util.Date;

public class RequestEntityListener {

	@PrePersist
	public void prePersist(Request request) {
        if (request.getCreatedDate() == null) {
        	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String formattedDate = dateFormat.format(new Date());
            request.setCreatedDate(formattedDate);
        }
    }
	
}

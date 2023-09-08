package com.inacif.rekognition.web.app.persistance;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.inacif.rekognition.web.app.entity.RoleFunctionality;

import jakarta.persistence.PrePersist;

public class RoleFunctionalityListener {
	
	@PrePersist
	public void prePersist(RoleFunctionality roleFunctionality) {
        if (roleFunctionality.getCreationDate() == null) {
        	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String formattedDate = dateFormat.format(new Date());
            roleFunctionality.setCreationDate(formattedDate);
        }
    }
	

}

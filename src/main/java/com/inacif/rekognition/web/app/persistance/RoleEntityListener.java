package com.inacif.rekognition.web.app.persistance;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.inacif.rekognition.web.app.entity.Role;

import jakarta.persistence.PrePersist;

public class RoleEntityListener {

	@PrePersist
	public void prePersist(Role role) {
        if (role.getCreationDate() == null) {
        	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String formattedDate = dateFormat.format(new Date());
            role.setCreationDate(formattedDate);
        }
    }
	
}

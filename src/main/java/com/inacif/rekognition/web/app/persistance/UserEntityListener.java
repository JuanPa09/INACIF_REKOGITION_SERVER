package com.inacif.rekognition.web.app.persistance;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.inacif.rekognition.web.app.entity.User;

import jakarta.persistence.PrePersist;

public class UserEntityListener {

	@PrePersist
	public void prePersist(User user) {
        if (user.getCreationDate() == null) {
        	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String formattedDate = dateFormat.format(new Date());
            user.setCreationDate(formattedDate);
        }
    }
	
}

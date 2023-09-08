package com.inacif.rekognition.web.app.entity;

import com.inacif.rekognition.web.app.persistance.RoleFunctionalityListener;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "Role_Functionality")
@EntityListeners(RoleFunctionalityListener.class)
public class RoleFunctionality {

	@EmbeddedId
	public RoleFunctionalityId id;
	private String creationDate;
	private String userModifies;

	public String getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}

	public RoleFunctionalityId getId() {
		return id;
	}

	public void setId(RoleFunctionalityId id) {
		this.id = id;
	}

	public String getUserModifies() {
		return userModifies;
	}

	public void setUserModifies(String userModifies) {
		this.userModifies = userModifies;
	}

}

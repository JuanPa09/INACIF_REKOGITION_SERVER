package com.inacif.rekognition.web.app.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Id;

@Embeddable
public class RoleFunctionalityId {
	
	public RoleFunctionalityId() {
		
	}
	
	public RoleFunctionalityId(Long roleId, Long functionalityId) {
		this.roleId = roleId;
		this.functionalityId = functionalityId;
	}

    private Long roleId;

    private Long functionalityId;

	public Long getRolId() {
		return roleId;
	}

	public void setRolId(Long rolId) {
		this.roleId = rolId;
	}

	public Long getFunctionalityId() {
		return functionalityId;
	}

	public void setFunctionalityId(Long functionalityId) {
		this.functionalityId = functionalityId;
	}  
}

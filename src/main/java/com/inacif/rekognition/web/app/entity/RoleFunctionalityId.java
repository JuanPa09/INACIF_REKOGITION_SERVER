package com.inacif.rekognition.web.app.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class RoleFunctionalityId {

	@Column(name = "role_id")
    private Long roleId;

    @Column(name = "functionality_id")
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

package com.inacif.rekognition.web.app.maps;

import java.util.List;

public class UpdateRoleFunctionality {

	Long roleId;
	List<FunctionalitiesStatus>  functionalitiesStatus;
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	public List<FunctionalitiesStatus> getFunctionalitiesStatus() {
		return functionalitiesStatus;
	}
	public void setFunctionalitiesStatus(List<FunctionalitiesStatus> functionalitiesStatus) {
		this.functionalitiesStatus = functionalitiesStatus;
	}
	
	
	
}

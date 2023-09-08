package com.inacif.rekognition.web.app.responses;

import java.util.List;

import com.inacif.rekognition.web.app.entity.Role;
import com.inacif.rekognition.web.app.projection.RolesFunctionalities;

public class FunctionalityRoleResultResponse {

	Role role;
	List<RolesFunctionalities> functionalities;
	
	public FunctionalityRoleResultResponse(Role role, List<RolesFunctionalities> functionalities) {
		this.role = role;
		this.functionalities = functionalities;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public List<RolesFunctionalities> getFunctionalities() {
		return functionalities;
	}

	public void setFunctionalities(List<RolesFunctionalities> functionalities) {
		this.functionalities = functionalities;
	}
	
	
	
}

package com.inacif.rekognition.web.app.maps;

public class RoleCreate {

	String name;
	String description;
	String userModifies;
	Integer[] functionalities;
	
	public String getUserModifies() {
		return userModifies;
	}
	public void setUserModifies(String userModifies) {
		this.userModifies = userModifies;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer[] getFunctionalities() {
		return functionalities;
	}
	public void setFunctionalities(Integer[] functionalities) {
		this.functionalities = functionalities;
	}
	
}

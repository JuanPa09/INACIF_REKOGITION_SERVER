package com.inacif.rekognition.web.app.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.inacif.rekognition.web.app.entity.CaseInfo;

public class OtherComparisonResult {

	private Long id;
	
	@JsonProperty("Caso")
	private String caseName;
	
	@JsonProperty("Color de ojos")
	private String eyes;
	
	@JsonProperty("Altura")
	private Integer height;
	
	@JsonProperty("Complexión")
	private String complexion;
	
	@JsonProperty("Sexo")
	private String sex;
	
	@JsonProperty("Descripción adicional")
	private String description;

	
	public OtherComparisonResult(CaseInfo caseInfo) {
		this.id = caseInfo.getId();
		this.caseName = caseInfo.getCaseName();
		this.eyes = caseInfo.getEyes();
		this.height = caseInfo.getHeight();
		//this.complexion = caseInfo
		this.sex = caseInfo.getSex();
		this.description = caseInfo.getAdditionalDescription();
	}
	
	public String getCaseName() {
		return caseName;
	}
	public void setCaseName(String caseName) {
		this.caseName = caseName;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEyes() {
		return eyes;
	}
	public void setEyes(String eyes) {
		this.eyes = eyes;
	}
	public Integer getHeight() {
		return height;
	}
	public void setHeight(Integer height) {
		this.height = height;
	}
	public String getComplexion() {
		return complexion;
	}
	public void setComplexion(String complexion) {
		this.complexion = complexion;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}

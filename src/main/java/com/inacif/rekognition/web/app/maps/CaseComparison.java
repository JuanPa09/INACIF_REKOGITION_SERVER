package com.inacif.rekognition.web.app.maps;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.inacif.rekognition.web.app.entity.CaseInfo;

public class CaseComparison {


	@JsonProperty("Fecha de levantamiento")
	private String surveyDate;
    
	@JsonProperty("Edad aproximada")
	private Integer age;
    
	@JsonProperty("Sexo")
	private String sex;
    
	@JsonProperty("Color de ojos")
	private String eyes;
    
	@JsonProperty("Color de cabello")
	private String hair;
    
	@JsonProperty("Color de piel")
	private String skin;
    
	@JsonProperty("Talla de pie (cm)")
	private Integer feet;
    
	@JsonProperty("Altura (cm)")
	private Integer height;
    
	@JsonProperty("Municipio de levantamiento")
	private String disappearanceMunicipality;
    
	@JsonProperty("Descripción Adicional")
	private String additionalDescription;

    
    public CaseComparison(CaseInfo caseInfo) {
    	this.surveyDate = caseInfo.getSurveyDate();
    	this.age = caseInfo.getAge();
    	this.sex = caseInfo.getSex();
    	this.eyes = caseInfo.getEyes();
    	this.hair = caseInfo.getHair();
    	this.skin = caseInfo.getSkin();
    	this.feet = caseInfo.getFeet();
    	this.height = caseInfo.getHeight();
    	this.disappearanceMunicipality = caseInfo.getDisappearanceMunicipality();
    	this.additionalDescription = caseInfo.getAdditionalDescription();
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getEyes() {
        return eyes;
    }

    public void setEyes(String eyes) {
        this.eyes = eyes;
    }

    public String getHair() {
        return hair;
    }

    public void setHair(String hair) {
        this.hair = hair;
    }

    public String getSkin() {
        return skin;
    }

    public void setSkin(String skin) {
        this.skin = skin;
    }

    public Integer getFeet() {
        return feet;
    }

    public void setFeet(Integer feet) {
        this.feet = feet;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public String getDisappearanceMunicipality() {
        return disappearanceMunicipality;
    }

    public void setDisappearanceMunicipality(String disappearanceMunicipality) {
        this.disappearanceMunicipality = disappearanceMunicipality;
    }

    public String getAdditionalDescription() {
        return additionalDescription;
    }

    public void setAdditionalDescription(String additionalDescription) {
        this.additionalDescription = additionalDescription;
    }

	public String getSurveyDate() {
		return surveyDate;
	}

	public void setSurveyDate(String surveyDate) {
		this.surveyDate = surveyDate;
	}
}
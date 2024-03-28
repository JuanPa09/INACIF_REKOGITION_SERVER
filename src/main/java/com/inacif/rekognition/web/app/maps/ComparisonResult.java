package com.inacif.rekognition.web.app.maps;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ComparisonResult {

	@JsonProperty("Imagen")
	private Float image;
	
	@JsonProperty("Color de ojos")
	private Integer eyes;
	
	@JsonProperty("Cabello")
	private Integer hair;
	
	@JsonProperty("Pie")
	private Integer feet;
	
	@JsonProperty("Edad")
	private Integer age;
	
	@JsonProperty("Municipio de levantamiento")
	private Integer municipality;
	
	@JsonProperty("Altura")
	private Integer height;
	
//	@JsonProperty("Complexión")
//	private Integer complexion;
	
	@JsonProperty("Sexo")
	private Integer sex;
	
	@JsonProperty("Descripción Adicional")
	private String description;
	
	
	public Float getImage() {
		return image;
	}
	public void setImage(Float image) {
		this.image = image;
	}
	public Integer getEyes() {
		return eyes;
	}
	public void setEyes(Integer eyes) {
		this.eyes = eyes;
	}
	public Integer getHair() {
		return hair;
	}
	public void setHair(Integer hair) {
		this.hair = hair;
	}
	public Integer getFeet() {
		return feet;
	}
	public void setFeet(Integer feet) {
		this.feet = feet;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public Integer getMunicipality() {
		return municipality;
	}
	public void setMunicipality(Integer municipality) {
		this.municipality = municipality;
	}
	public Integer getHeight() {
		return height;
	}
	public void setHeight(Integer height) {
		this.height = height;
	}
//	public Integer getComplexion() {
//		return complexion;
//	}
//	public void setComplexion(Integer complexion) {
//		this.complexion = complexion;
//	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
		
}

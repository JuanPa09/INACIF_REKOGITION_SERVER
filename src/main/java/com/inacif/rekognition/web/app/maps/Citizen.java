package com.inacif.rekognition.web.app.maps;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.inacif.rekognition.web.app.Utils;
import com.inacif.rekognition.web.app.entity.Request;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

public class Citizen {
	
	public Citizen(Request request) {
		this.setAdditionalDescription(request.getAdditionalDescription());
		this.setDisappearedBirthdate(request.getDisappearedBirthdate());
		this.setDisappearedComplexion(request.getDisappearedComplexion());
		this.setDisappearedEyesColor(request.getDisappearedEyesColor());
		this.setDisappearedFeetSize(request.getDisappearedFeetSize());
		this.setDisappearedFirstName(request.getDisappearedFirstName());
		this.setDisappearedHairColor(request.getDisappearedHairColor());
		this.setDisappearedHeight(request.getDisappearedHeight());
		this.setDisappearedLastName(request.getDisappearedLastName());
		this.setDisappearedMunicipality(request.getDisappearedMunicipality());
		this.setDisappearedOtherNames(request.getDisappearedOtherNames());
		this.setDisappearedSecondLastName(request.getDisappearedSecondLastName());
		this.setDisappearedSecondName(request.getDisappearedSecondName());
		this.setDisappearedSex(request.getDisappearedSex());
		this.setDisappearedSkin(request.getDisappearedSkin());
	}
    
	@JsonProperty("Primer Nombre")
    private String disappearedFirstName;
	
	@JsonProperty("Segundo Nombre")
    private String disappearedSecondName;
    
	@JsonProperty("Otros Nombres")
	private String disappearedOtherNames;
	
	@JsonProperty("Primer Apellido")
    private String disappearedLastName;
	
	@JsonProperty("Segundo Apellido")
    private String disappearedSecondLastName;
    
	@JsonProperty("Fecha de nacimiento")
    private String disappearedBirthdate;
    
	@JsonProperty("Altura (cm)")
    private int disappearedHeight;
	
	@JsonProperty("Municipio")
    private String disappearedMunicipality;
	
	@JsonProperty("Complexión")
    private String disappearedComplexion;
	
	@JsonProperty("Color de ojos")
    private String disappearedEyesColor;
	
	@JsonProperty("Color de cabello")
    private String disappearedHairColor;
	
	@JsonProperty("Talla del pie (cm)")
    private int disappearedFeetSize;
	
	@JsonProperty("Color de piel")
    private String disappearedSkin;
	
	@JsonProperty("Sexo")
    private String disappearedSex;

	@JsonProperty("Descripción Adicional")
    private String additionalDescription;
    

	public String getDisappearedFirstName() {
		return disappearedFirstName;
	}

	public void setDisappearedFirstName(String disappearedFirstName) {
		this.disappearedFirstName = disappearedFirstName;
	}

	public String getDisappearedSecondName() {
		return disappearedSecondName;
	}

	public void setDisappearedSecondName(String disappearedSecondName) {
		this.disappearedSecondName = disappearedSecondName;
	}

	public String getDisappearedOtherNames() {
		return disappearedOtherNames;
	}

	public void setDisappearedOtherNames(String disappearedOtherNames) {
		this.disappearedOtherNames = disappearedOtherNames;
	}

	public String getDisappearedLastName() {
		return disappearedLastName;
	}

	public void setDisappearedLastName(String disappearedLastName) {
		this.disappearedLastName = disappearedLastName;
	}

	public String getDisappearedSecondLastName() {
		return disappearedSecondLastName;
	}

	public void setDisappearedSecondLastName(String disappearedSecondLastName) {
		this.disappearedSecondLastName = disappearedSecondLastName;
	}

	public String getDisappearedBirthdate() {
		return Utils.getDateWithFormat(disappearedBirthdate);
	}

	public void setDisappearedBirthdate(String disappearedBirthdate) {
		this.disappearedBirthdate = disappearedBirthdate;
	}

	public int getDisappearedHeight() {
		return disappearedHeight;
	}

	public void setDisappearedHeight(int disappearedHeight) {
		this.disappearedHeight = disappearedHeight;
	}

	public String getDisappearedMunicipality() {
		return disappearedMunicipality;
	}

	public void setDisappearedMunicipality(String disappearedMunicipality) {
		this.disappearedMunicipality = disappearedMunicipality;
	}

	public String getDisappearedComplexion() {
		return disappearedComplexion;
	}

	public void setDisappearedComplexion(String disappearedComplexion) {
		this.disappearedComplexion = disappearedComplexion;
	}

	public String getDisappearedEyesColor() {
		return disappearedEyesColor;
	}

	public void setDisappearedEyesColor(String disappearedEyesColor) {
		this.disappearedEyesColor = disappearedEyesColor;
	}

	public String getDisappearedHairColor() {
		return disappearedHairColor;
	}

	public void setDisappearedHairColor(String disappearedHairColor) {
		this.disappearedHairColor = disappearedHairColor;
	}

	public int getDisappearedFeetSize() {
		return disappearedFeetSize;
	}

	public void setDisappearedFeetSize(int disappearedFeetSize) {
		this.disappearedFeetSize = disappearedFeetSize;
	}

	public String getDisappearedSkin() {
		return disappearedSkin;
	}

	public void setDisappearedSkin(String disappearedSkin) {
		this.disappearedSkin = disappearedSkin;
	}

	public String getDisappearedSex() {
		return disappearedSex;
	}

	public void setDisappearedSex(String disappearedSex) {
		this.disappearedSex = disappearedSex;
	}

	public String getAdditionalDescription() {
		return additionalDescription;
	}

	public void setAdditionalDescription(String additionalDescription) {
		this.additionalDescription = additionalDescription;
	}
	
}
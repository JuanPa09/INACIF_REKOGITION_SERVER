package com.inacif.rekognition.web.app.projection;



import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

public interface RequestCitizenDetail {

	@JsonProperty("Primer Nombre")
    String getDisappearedFirstName();
	
	@JsonProperty("Segundo Nombre")
    String getDisappearedSecondName();
    
	@JsonProperty("Otros Nombres")
	String getDisappearedOtherNames();
	
	@JsonProperty("Primer Apellido")
    String getDisappearedLastName();
	
	@JsonProperty("Segundo Apellido")
    String getDisappearedSecondLastName();
    
	@JsonProperty("Fecha de nacimiento")
    @Temporal(TemporalType.DATE)
    String getDisappearedBirthdate();
    
	@JsonProperty("Altura cm")
    int getDisappearedHeight();
	
	@JsonProperty("Municipio")
    String getDisappearedMunicipality();
	
	@JsonProperty("Complexión")
    String getDisappearedComplexion();
	
	@JsonProperty("Color de ojos")
    String getDisappearedEyesColor();
	
	@JsonProperty("Color de cabello")
    String getDisappearedHairColor();
	
	@JsonProperty("Talla del pie (cm)")
    int getDisappearedFeetSize();
	
	@JsonProperty("Color de piel")
    String getDisappearedSkin();
	
	@JsonProperty("Sexo")
    String getDisappearedSex();

	@JsonProperty("Descripción Adicional")
    String getAdditionalDescription();
	
}

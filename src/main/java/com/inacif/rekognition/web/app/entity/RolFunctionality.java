package com.inacif.rekognition.web.app.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Data
@Entity
@Table(name = "Rol_Functionality")
public class RolFunctionality {

    @EmbeddedId
    private RoleFunctionalityId id;

    private String userModifies;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Integer creationDate;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Integer modificationDate;
    
    // Getters y setters
}
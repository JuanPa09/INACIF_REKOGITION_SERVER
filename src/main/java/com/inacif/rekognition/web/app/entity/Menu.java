package com.inacif.rekognition.web.app.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Data
@Entity
@Table(name = "Menu")
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int level;
    private Long fatherId;
    private String userModifies;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Integer creationDate;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Integer modificationDate;
    
    private String keyRoute;
    
    // Getters y setters
}
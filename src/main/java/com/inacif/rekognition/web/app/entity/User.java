package com.inacif.rekognition.web.app.entity;




import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Data
@Entity
@Table(name = "User")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String names;
    private String lastNames;
    private String email;
    private int phone;
    private String password;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Long creationDate;
    
    @ManyToOne
    @JoinColumn(name = "status_id")
    private Status status;
    
    @ManyToOne
    @JoinColumn(name = "rol_id")
    private Role rol;
    
    @ManyToOne
    @JoinColumn(name = "campus_id")
    private Campus campus;
    
    // Getters y setters
}

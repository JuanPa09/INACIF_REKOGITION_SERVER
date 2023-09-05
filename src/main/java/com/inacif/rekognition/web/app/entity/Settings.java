package com.inacif.rekognition.web.app.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Settings")
public class Settings {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	private Integer eyes;
	private Integer hair;
	private Integer skin;
	private Integer feet;
	private Integer height;
	private Integer complexion;
	private Integer image;
	private Integer sex;
	private Integer maxRequests;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public Integer getSkin() {
		return skin;
	}
	public void setSkin(Integer skin) {
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
	public Integer getComplexion() {
		return complexion;
	}
	public void setComplexion(Integer complexion) {
		this.complexion = complexion;
	}
	public Integer getImage() {
		return image;
	}
	public void setImage(Integer image) {
		this.image = image;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public Integer getMaxRequests() {
		return maxRequests;
	}
	public void setMaxRequests(Integer maxRequests) {
		this.maxRequests = maxRequests;
	}
	
	
}

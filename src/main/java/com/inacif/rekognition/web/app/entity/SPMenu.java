package com.inacif.rekognition.web.app.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class SPMenu {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	String name;
	
	@Column(name = "short")
	Integer shortLevel;
	Integer level;
	Integer fatherId;
	String icon;
	String keyRoute;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getShortLevel() {
		return shortLevel;
	}
	public void setShortLevel(Integer shortLevel) {
		this.shortLevel = shortLevel;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	public Integer getFatherId() {
		return fatherId;
	}
	public void setFatherId(Integer fatherId) {
		this.fatherId = fatherId;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getKeyRoute() {
		return keyRoute;
	}
	public void setKeyRoute(String keyRoute) {
		this.keyRoute = keyRoute;
	}
	
	
	
}

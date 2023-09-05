package com.inacif.rekognition.web.app.entity;

public class RekognitionInfo {
	
	private String imageName;
	private Float similarity;
	
	public String getImageName() {
		return imageName;
	}
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	public Float getSimilarity() {
		return similarity;
	}
	public void setSimilarity(Float similarity) {
		this.similarity = similarity;
	} 
}

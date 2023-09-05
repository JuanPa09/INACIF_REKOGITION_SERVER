package com.inacif.rekognition.web.app.entity;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class Rekognition {
	
	private List<RekognitionInfo> data = new ArrayList<>();

	public List<RekognitionInfo> getData() {
		return data;
	}

	public void setData(List<RekognitionInfo> data) {
		this.data = data;
	} 
	
	public void addElementToData(RekognitionInfo element) {
		this.data.add(element);
	}

}

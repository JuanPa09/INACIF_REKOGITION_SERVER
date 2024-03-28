package com.inacif.rekognition.web.app.service;


import com.inacif.rekognition.web.app.entity.Rekognition;

public interface FaceComparisonService {
	
	public Rekognition compare(String bucketName, String referenceImageName, float similarity);

}

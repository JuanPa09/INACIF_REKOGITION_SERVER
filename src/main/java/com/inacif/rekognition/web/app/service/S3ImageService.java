package com.inacif.rekognition.web.app.service;

import java.util.List;

public interface S3ImageService {

	public String upload(String base64Image, String folderName);
	
	public List<String> listImageNamesInBucket(String bucketName, String referenceImageName);
	
	public String getSignedUrl(String imageName);
	
}

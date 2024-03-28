package com.inacif.rekognition.web.app.service;

import java.io.ByteArrayInputStream;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.amazonaws.services.s3.model.ListObjectsV2Request;
import com.amazonaws.services.s3.model.ListObjectsV2Result;
import com.inacif.rekognition.web.app.Constants;
import com.inacif.rekognition.web.app.Utils;

@Service
public class S3ImageServiceImpl implements S3ImageService {

	@Autowired
	private AmazonS3 amazonS3;

    /*@Autowired
    public S3ImageServiceImpl(AmazonS3 amazonS3) {
        this.amazonS3 = amazonS3;
    }*/
	
	
	@Override
	public String upload(String base64Image, String folderName) {
		byte[] imageBytes = java.util.Base64.getDecoder().decode(base64Image);
		ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(imageBytes.length);
        String formattedDate = Utils.getTimeZoneWithFormat("yyyyMMdd-HH:mm:ss");
        String uniqueId = UUID.randomUUID().toString();
        String imageName = folderName + "/"  + formattedDate + "_" + uniqueId + ".jpeg";
        System.out.println("Subiendo: " + imageName);
        amazonS3.putObject(Constants.bucketName, imageName, new ByteArrayInputStream(imageBytes), metadata);
        return imageName;
	}
	
	/*
	 * Gets the list of images in a bucket excluding the reference image name
	 * */
//	public List<String> listImageNamesInBucket(String bucketName, String referenceImageName) {
//	    List<S3ObjectSummary> objectSummaries = amazonS3.listObjects(bucketName, referenceImageName)
//	    												.pre
//	                                                    .getObjectSummaries();
//
//	    List<String> imageNamesToCompare = objectSummaries.stream()
//	        .map(S3ObjectSummary::getKey)
//	        .collect(Collectors.toList());
//
//	    return imageNamesToCompare;
//	}
	
	public List<String> listImageNamesInBucket(String bucketName, String folderName) {

	    List<String> imageNamesToCompare = new ArrayList<>();
	    String prefix = folderName + "/";
	    String token = null;

	    do {
	        ListObjectsV2Request request = new ListObjectsV2Request()
	                .withBucketName(bucketName)
	                .withPrefix(prefix)
	                .withContinuationToken(token);
	        ListObjectsV2Result result = amazonS3.listObjectsV2(request);
	        for (S3ObjectSummary objectSummary : result.getObjectSummaries()) {
	            imageNamesToCompare.add(objectSummary.getKey());
	        }
	        token = result.getNextContinuationToken();
	    } while (token != null);

	    return imageNamesToCompare;
	}


	@Override
	public String getSignedUrl(String imageName) {
		Instant expiration = Instant.now().plus(Duration.ofHours(1));
        Date expirationDate = Date.from(expiration);
		return amazonS3.generatePresignedUrl(Constants.bucketName, imageName, expirationDate).toString();
	}

}

package com.inacif.rekognition.web.app.service;

import java.io.ByteArrayInputStream;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3ObjectSummary;
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
	public String upload(String base64Image, String prefix) {
		byte[] imageBytes = java.util.Base64.getDecoder().decode(base64Image);
		ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(imageBytes.length);
        String formattedDate = Utils.getTimeZoneWithFormat("yyyyMMdd-HH:mm:ss");
        String uniqueId = UUID.randomUUID().toString();
        String imageName = prefix + "-"  + formattedDate + "-" + uniqueId + ".jpeg";
        amazonS3.putObject(Constants.bucketName, imageName, new ByteArrayInputStream(imageBytes), metadata);
        return imageName;
	}
	
	/*
	 * Gets the list of images in a bucket excluding the reference image name
	 * */
	public List<String> listImageNamesInBucket(String bucketName, String referenceImageName) {
        List<S3ObjectSummary> objectSummaries = amazonS3.listObjects(bucketName).getObjectSummaries();
        List<String> imageNamesToCompare = new ArrayList<>();
        for (S3ObjectSummary objectSummary : objectSummaries) {
            String imageName = objectSummary.getKey();
            
            // Add images to list when is different from current image and is a case image
            if (!imageName.equals(referenceImageName) && imageName.split("-")[0].equals("Case")) {
                imageNamesToCompare.add(imageName);
            }
        }
        return imageNamesToCompare;
    }


	@Override
	public String getSignedUrl(String imageName) {
		Instant expiration = Instant.now().plus(Duration.ofHours(1));
        Date expirationDate = Date.from(expiration);
		return amazonS3.generatePresignedUrl(Constants.bucketName, imageName, expirationDate).toString();
	}

}

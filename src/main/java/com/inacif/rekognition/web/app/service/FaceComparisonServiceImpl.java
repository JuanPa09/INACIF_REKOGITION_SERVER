package com.inacif.rekognition.web.app.service;


import com.amazonaws.services.rekognition.AmazonRekognition;
import com.amazonaws.services.rekognition.model.CompareFacesMatch;
import com.amazonaws.services.rekognition.model.CompareFacesRequest;
import com.amazonaws.services.rekognition.model.CompareFacesResult;
import com.amazonaws.services.rekognition.model.Image;
import com.amazonaws.services.s3.AmazonS3;
import com.inacif.rekognition.web.app.entity.Rekognition;
import com.inacif.rekognition.web.app.entity.RekognitionInfo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FaceComparisonServiceImpl implements FaceComparisonService {
	
	private final AmazonRekognition rekognitionClient;
	//private final AmazonS3 amazonS3;
	
	@Autowired
	private S3ImageService s3ImageService;

    @Autowired
    public FaceComparisonServiceImpl(AmazonRekognition rekognitionClient, AmazonS3 amazonS3) {
        this.rekognitionClient = rekognitionClient;
        //this.amazonS3 = amazonS3;
    }

	
	public Rekognition compare(String bucketName, String referenceImageName) {
		
		Rekognition rekognitionResult = new Rekognition();
		Image referenceImage = new Image().withS3Object(new com.amazonaws.services.rekognition.model.S3Object()
                .withBucket(bucketName).withName(referenceImageName));

		System.out.println("Comparando imagenes");
        List<String> imageNamesToCompare = s3ImageService.listImageNamesInBucket(bucketName, referenceImageName);    
        for (String imageName : imageNamesToCompare) {
        	System.out.println(imageName);
            Image imageToCompare = new Image().withS3Object(new com.amazonaws.services.rekognition.model.S3Object()
                    .withBucket(bucketName).withName(imageName));
            CompareFacesRequest request = new CompareFacesRequest()
                    .withSourceImage(referenceImage)
                    .withTargetImage(imageToCompare)
                    .withSimilarityThreshold(60.0f);

            CompareFacesResult result = rekognitionClient.compareFaces(request);

            for (CompareFacesMatch match : result.getFaceMatches()) {
                if (match.getSimilarity() > 80.0f) { 
                	RekognitionInfo imageInfo = new RekognitionInfo();
                	imageInfo.setImageName(imageName);
                	imageInfo.setSimilarity(match.getSimilarity());
                	rekognitionResult.addElementToData(imageInfo);
                    break;
                }
            }
        }

        return rekognitionResult;
	}

}

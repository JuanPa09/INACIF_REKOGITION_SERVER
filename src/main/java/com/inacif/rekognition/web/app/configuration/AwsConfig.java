package com.inacif.rekognition.web.app.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.rekognition.AmazonRekognition;
import com.amazonaws.services.rekognition.AmazonRekognitionClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

//import io.github.cdimascio.dotenv.Dotenv;

@Configuration
public class AwsConfig {

	@Bean
    public AmazonS3 amazonS3() {
		//Dotenv dotenv = Dotenv.load();
        String accessKey = "AKIA2UHQ4R4JRQMGV2S3";
        String secretKey = "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";
        
        System.out.println("Imprimiendo accessKey: " + accessKey);
        
        BasicAWSCredentials awsCredentials = new BasicAWSCredentials(accessKey, secretKey);

        return AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .withRegion(Regions.US_EAST_1)
                .build();
    }
	
	@Bean
    public AmazonRekognition amazonRekognition() {
        BasicAWSCredentials credentials = new BasicAWSCredentials("AKIA2UHQ4R4JRQMGV2S3", "DRW3MxrntakV1OsrlVEp+VyvoCk1Ot8CyGiDRO/r");
        return AmazonRekognitionClientBuilder
                .standard()
                .withRegion(Regions.US_EAST_1)
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .build();
    }
	
	
	/*@Bean
    public AmazonRekognition amazonRekognition() {

		
        /*AWSSecurityTokenService stsClient = AWSSecurityTokenServiceClientBuilder.defaultClient();
        AssumeRoleRequest assumeRoleRequest = new AssumeRoleRequest()
                .withRoleArn("arn:aws:iam::730649628435:role/RekognitionRole")
                .withRoleSessionName("RekognitionRoleSession");
        /*AssumeRoleResult assumeRoleResult = stsClient.assumeRole(assumeRoleRequest);
        BasicSessionCredentials sessionCredentials = new BasicSessionCredentials(
                assumeRoleResult.getCredentials().getAccessKeyId(),
                assumeRoleResult.getCredentials().getSecretAccessKey(),
                assumeRoleResult.getCredentials().getSessionToken());*/
		
		/*
        return AmazonRekognitionClientBuilder.standard()
                .crede(DefaultCredentialsProvider.create())
                .withRegion(Regions.US_EAST_1)  
                .build();
    }*/
	
	/*@Bean
    public AmazonRekognition amazonRekognition() {
		Dotenv dotenv = Dotenv.load();
        String accessKey = dotenv.get("AWS_ACCESS_KEY_ID");
        String secretKey = dotenv.get("AWS_SECRET_ACCESS_KEY");

        AWSSecurityTokenService stsClient = AWSSecurityTokenServiceClientBuilder.standard()
                .withRegion(Regions.US_EAST_1)
                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(accessKey, secretKey)))
                .build();
        AWSCredentialsProvider sessionCredentials = new  STSAssumeRoleSessionCredentialsProvider
                .Builder("", "")
                .withStsClient(stsClient)
                .build();

        return AmazonRekognitionClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider((AWSCredentials) sessionCredentials))
                .withRegion("us-east-1")
                .build();
    }*/
	
}

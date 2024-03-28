package com.inacif.rekognition.web.app.service;

import java.lang.reflect.Field;

import org.springframework.stereotype.Service;

import com.inacif.rekognition.web.app.Utils;
import com.inacif.rekognition.web.app.entity.CaseInfo;
import com.inacif.rekognition.web.app.entity.Request;
import com.inacif.rekognition.web.app.entity.Settings;
import com.inacif.rekognition.web.app.maps.ComparisonResult;

@Service
public class DataProcessorServiceImpl implements DataProcessorService {

	
	
	
	@Override
	public ComparisonResult getComparisonPercentages(Request requestInfo, CaseInfo caseInfo) {
		ComparisonResult comparisonResult = new ComparisonResult();
		
		comparisonResult.setAge( this.getIntegerPercentage(Utils.getAgeByDate(requestInfo.getDisappearedBirthdate().toString()), caseInfo.getAge()) );
		//comparisonResult.setComplexion(this.getStringPercentage(requestInfo.getDisappearedComplexion(), caseInfo.get));
		comparisonResult.setDescription(requestInfo.getAdditionalDescription());
		comparisonResult.setEyes(this.getStringPercentage(requestInfo.getDisappearedEyesColor(), caseInfo.getEyes()));
		comparisonResult.setFeet(this.getIntegerPercentage(requestInfo.getDisappearedFeetSize(), caseInfo.getFeet()));
		comparisonResult.setHair(this.getStringPercentage(requestInfo.getDisappearedHairColor(), caseInfo.getHair()));
		comparisonResult.setHeight(this.getIntegerPercentage(requestInfo.getDisappearedHeight(), caseInfo.getHeight()));
		comparisonResult.setMunicipality(this.getStringPercentage(requestInfo.getDisappearedMunicipality(), caseInfo.getDisappearanceMunicipality()));
		comparisonResult.setSex(this.getStringPercentage(requestInfo.getDisappearedSex(), caseInfo.getSex()));
		
		return comparisonResult;
	}
	
	public boolean isOnFilters(Settings settings, ComparisonResult comparisonResult) {
		
		if (settings.getEyes() > comparisonResult.getEyes()) {
			System.out.println("Falla ojos " + settings.getEyes() + " " + comparisonResult.getEyes());
            return false;
        }
        if (settings.getHair() > comparisonResult.getHair()) {
        	System.out.println("Falla pelo ");
            return false;
        }
        if (settings.getFeet() > comparisonResult.getFeet()) {
        	System.out.println("Falla pie ");
            return false;
        }
        if (settings.getHeight() > comparisonResult.getHeight()) {
        	System.out.println("Falla altura ");
            return false;
        }
//        if (settings.getComplexion() > comparisonResult.getComplexion()) {
//        	System.out.println("Falla complexion ");
//            return false;
//        }
        if (settings.getSex() > comparisonResult.getSex()) {
        	System.out.println("Falla sexo ");
            return false;
        }
       
        return true;
		
	}
	
	private Integer getIntegerPercentage(Integer requestValue, Integer caseValue){
		Integer max,min = 0;
		
		if(requestValue > caseValue) {
			max = requestValue;
			min = caseValue;
		}else {
			max = caseValue;
			min = requestValue;
		}
		
		return (min * 100)/max;
	}
	
	private Integer getStringPercentage(String requestValue, String caseValue) {
		if(requestValue.equals(caseValue))
			return 100;
		return 0;
	}

}

package com.inacif.rekognition.web.app.service;

import com.inacif.rekognition.web.app.entity.CaseInfo;
import com.inacif.rekognition.web.app.entity.Request;
import com.inacif.rekognition.web.app.entity.Settings;
import com.inacif.rekognition.web.app.maps.ComparisonResult;

public interface DataProcessorService {

		
	public ComparisonResult getComparisonPercentages(Request requestInfo, CaseInfo caseInfo);
	
	public boolean isOnFilters(Settings settings, ComparisonResult comparisonResult);
	
}

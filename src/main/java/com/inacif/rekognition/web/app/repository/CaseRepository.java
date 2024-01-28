package com.inacif.rekognition.web.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inacif.rekognition.web.app.entity.CaseInfo;
import com.inacif.rekognition.web.app.projection.CaseReport;

@Repository
public interface CaseRepository extends JpaRepository<CaseInfo, Long> {

	List<CaseInfo> findByImageIn(List<String> names);
	
	Optional<List<CaseReport>> findByCreatedDateBetween(String initDate, String endDate);
	
}

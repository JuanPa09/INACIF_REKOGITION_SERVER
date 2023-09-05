package com.inacif.rekognition.web.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inacif.rekognition.web.app.entity.CaseInfo;

@Repository
public interface CaseRepository extends JpaRepository<CaseInfo, Long> {

	List<CaseInfo> findByImageIn(List<String> names);
}

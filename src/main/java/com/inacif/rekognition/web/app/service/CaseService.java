package com.inacif.rekognition.web.app.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.inacif.rekognition.web.app.entity.CaseInfo;

public interface CaseService  {
	
	public Iterable<CaseInfo> findAll();
	
	public Page<CaseInfo> findAll(Pageable pageable);
	
	public Optional<CaseInfo> findById(Long id);
	
	public CaseInfo save(CaseInfo caseInfo);
	
	public void deleteById(Long id);
	
}

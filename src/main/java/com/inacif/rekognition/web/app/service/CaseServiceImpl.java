package com.inacif.rekognition.web.app.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.inacif.rekognition.web.app.entity.CaseInfo;
import com.inacif.rekognition.web.app.repository.CaseRepository;

@Service
public class CaseServiceImpl implements CaseService {

	@Autowired
	private CaseRepository caseRepository;
	
	@Autowired
	private S3ImageService s3ImageService;
	
	@Override
	@Transactional(readOnly = true)
	public Iterable<CaseInfo> findAll() {
		return caseRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<CaseInfo> findAll(Pageable pageable) {
		return caseRepository.findAll(pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<CaseInfo> findById(Long id) {
		return caseRepository.findById(id);
	}

	@Override
	@Transactional
	public CaseInfo save(CaseInfo caseInfo) {
		String imageName = s3ImageService.upload(caseInfo.getImage(), "Case");
		caseInfo.setImage(imageName);
		return caseRepository.save(caseInfo);
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		caseRepository.deleteById(id);
	}

	

}

package com.inacif.rekognition.web.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inacif.rekognition.web.app.entity.ConfirmationCode;

public interface ConfirmationCodeRepository extends JpaRepository<ConfirmationCode, Long> {

	public Optional<ConfirmationCode> findByCode(String code);
	
}

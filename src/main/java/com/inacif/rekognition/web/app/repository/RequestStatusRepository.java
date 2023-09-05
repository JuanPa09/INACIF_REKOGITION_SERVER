package com.inacif.rekognition.web.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inacif.rekognition.web.app.entity.RequestStatus;

public interface RequestStatusRepository extends JpaRepository<RequestStatus, Long> {
	
	Optional<RequestStatus> findByName(String name);
	
}

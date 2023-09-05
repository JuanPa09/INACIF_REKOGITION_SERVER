package com.inacif.rekognition.web.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inacif.rekognition.web.app.entity.Status;

public interface StatusRepository extends JpaRepository<Status, Long>{

	Optional<Status> findByName(String name);
}

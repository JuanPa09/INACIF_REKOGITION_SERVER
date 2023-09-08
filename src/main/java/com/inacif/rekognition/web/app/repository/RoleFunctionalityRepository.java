package com.inacif.rekognition.web.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inacif.rekognition.web.app.entity.RoleFunctionality;
import com.inacif.rekognition.web.app.entity.RoleFunctionalityId;

public interface RoleFunctionalityRepository extends JpaRepository<RoleFunctionality, RoleFunctionalityId> {

	List<RoleFunctionality> findByidRoleId(Long id);
	
}

package com.inacif.rekognition.web.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inacif.rekognition.web.app.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
	
}

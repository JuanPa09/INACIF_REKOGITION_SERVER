package com.inacif.rekognition.web.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.inacif.rekognition.web.app.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
	
	Optional<Role>getRoleByName(String name);
	
	@Query("SELECT r.id FROM Role r WHERE r.name IN :listNames")
	Optional<List<Long>> getRoleIdsByName(@Param("listNames") List<String> listNames);
	
}

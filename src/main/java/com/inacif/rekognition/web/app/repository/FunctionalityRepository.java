package com.inacif.rekognition.web.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.inacif.rekognition.web.app.entity.Functionality;
import com.inacif.rekognition.web.app.projection.RolesFunctionalities;

public interface FunctionalityRepository extends JpaRepository<Functionality, Long> {

	@Query(
			"SELECT f.id as id, f.name as name"
			+ " FROM Functionality f"
			+ " JOIN RoleFunctionality rf ON rf.id.functionalityId = f.id"
			+ " WHERE rf.id.roleId = :roleId"
			)
	List<RolesFunctionalities> findByidRoleId(Long roleId);
	
}

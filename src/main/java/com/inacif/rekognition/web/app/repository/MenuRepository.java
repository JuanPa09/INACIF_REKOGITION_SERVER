package com.inacif.rekognition.web.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import com.inacif.rekognition.web.app.entity.SPMenu;

public interface MenuRepository extends JpaRepository<SPMenu, Long> {

	@Procedure("spMenuByRole")
	List<SPMenu> callMenuProcedure(@Param("in_roles_array") String roles);
	
}

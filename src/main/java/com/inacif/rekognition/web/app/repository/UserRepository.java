package com.inacif.rekognition.web.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.inacif.rekognition.web.app.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	@Query("SELECT u FROM User u WHERE u.username = :username AND u.password = :password")
    List<User> findByUsernameAndPassword(String username, String password);
	
}

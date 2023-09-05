package com.inacif.rekognition.web.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inacif.rekognition.web.app.entity.Settings;

public interface SettingsRepository extends JpaRepository<Settings, Long> {
	
}

package com.inacif.rekognition.web.app.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfiguration {

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
				.allowedOrigins("http://localhost:4200")  // Allow requests from any origin
	            .allowedMethods("GET", "POST", "PUT", "DELETE") // Allowed HTTP methods
	            .allowedHeaders("*") // Allow all headers
	            .exposedHeaders("Authorization") // Expose specific headers if needed
	            .allowCredentials(true)
	            .maxAge(3600); // Max age of preflight requests
			}
		};
		
	}
	
}

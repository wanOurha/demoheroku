package com.example.demoheroku.config;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// ระบุ CORS แบบ global หรือ ทั้ง service
//@Configuration
public class CorsConfig implements WebMvcConfigurer {

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		// default httpMethod is GET, POST, HEAD
		// ....allowedOrigins("domain1") only domain1 are access.
		// registry.addMapping("/product/*").allowedMethods("*") is all httpMethod can
		// access.
		// registry.addMapping("/**"); all domain all method
		// .addMapping("/product").allowedMethods("GET", "POST") /product can GET,POST

		registry.addMapping("/product").allowedMethods("GET", "POST").allowedOrigins("http://localhost:3000");
		registry.addMapping("/auth/login").allowedMethods("POST").allowedOrigins("http://localhost:3000");

		registry.addMapping("/product").allowedMethods("GET", "POST").allowedOrigins("https://fir-host1-f1c19.web.app");
		registry.addMapping("/auth/login").allowedMethods("POST").allowedOrigins("https://fir-host1-f1c19.web.app");

	}
}

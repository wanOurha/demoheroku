package com.example.demoheroku.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

// BeanFactory for BCryptPasswordEncoder dependencies
// Using generated security password: a0888527-4ed4-4718-ac88-47f297023edd
// spring ทำ auth ให้ จาก dependencies 
// security เมื่อ ระบุแล้ว เป็นแบบ basic ต้องยิง username password
@Configuration
public class AppConfig {

	@Bean
	BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

}

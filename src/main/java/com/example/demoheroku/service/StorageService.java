package com.example.demoheroku.service;

import org.springframework.web.multipart.MultipartFile;

public interface StorageService {

	void init();

	// upload function
	String store(MultipartFile file);

}

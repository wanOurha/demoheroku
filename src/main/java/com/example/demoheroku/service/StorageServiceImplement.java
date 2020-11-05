package com.example.demoheroku.service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demoheroku.exception.StorageException;

// Layer ที่บอกว่านี้คือ service ใช้เหมือ component
@Service
public class StorageServiceImplement implements StorageService {

	// "${app.upload.path:images}" is format of file value path
	@Value("${app.upload.path:images}")
	private String path;
	private Path rootLocation;

	@Override
	public void init() {
		// new folder add this function by key with properties
		this.rootLocation = Paths.get(path);
		try {
			Files.createDirectories(rootLocation);

		} catch (IOException ex) {
			throw new StorageException(ex.getMessage());
		}

	}

	@Override
	public String store(MultipartFile file) {
		// TODO Auto-generated method stub
		if (file == null || file.isEmpty()) {
			return null;
		}

		String fileName = org.springframework.util.StringUtils.cleanPath(file.getOriginalFilename());
		try {
			if (fileName.contains("..")) {
				throw new StorageException("path outside current directory: ");
			}
			fileName = UUID.randomUUID() + "." + fileName.substring(fileName.lastIndexOf("."));

			try (InputStream inputStream = file.getInputStream()) {
				Files.copy(inputStream, this.rootLocation.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
				return fileName;
			}
		} catch (IOException ex) {
			throw new StorageException("faile to store file: " + fileName + ex.getMessage());
		}

	}

}

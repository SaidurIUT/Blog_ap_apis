package com.mathanosto.blog.services.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mathanosto.blog.services.FileService;

@Service
public class FileServiceImpl implements FileService {

	@Override
	public String uploadImage(String path, MultipartFile file) throws IOException {

		String name = file.getOriginalFilename();

		String randomId = UUID.randomUUID().toString();

		String fileName1 = randomId.concat(name.substring(name.lastIndexOf(".")));

		Path filePath = Paths.get(path).resolve(fileName1);

		// Ensure the parent directory exists
		if (Files.notExists(filePath.getParent())) {
			Files.createDirectories(filePath.getParent());
		}

		// Copy file to the target location
		Files.copy(file.getInputStream(), filePath);

		return fileName1;
	}

	@Override
	public InputStream getResource(String path, String fileName) throws FileNotFoundException {
		String fullPath = path + File.separator + fileName;
		InputStream inputStream = new FileInputStream(fullPath);
		return inputStream;
	}

}

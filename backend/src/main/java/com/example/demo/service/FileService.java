package com.example.demo.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService {

	public static final String RESOURCES_PATH = "src/main/resources/pdf/";

	public String store(MultipartFile file) throws IOException {
		String fileName = file.getOriginalFilename().replace(".pdf", "") + "_" + new Date().getTime() + ".pdf";
		FileOutputStream fout = new FileOutputStream(new File(RESOURCES_PATH + fileName));
		fout.write(file.getBytes());
		fout.close();
		return fileName;
	}

}

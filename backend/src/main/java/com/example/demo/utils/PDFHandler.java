package com.example.demo.utils;

import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.io.RandomAccessFile;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.text.PDFTextStripper;

import com.example.demo.service.FileService;

public class PDFHandler {

	public static String parse(String fileName) throws IOException {
		PDFParser parser = new PDFParser(new RandomAccessFile(new File(FileService.RESOURCES_PATH + fileName), "r"));
		parser.parse();
		String result = new PDFTextStripper().getText(parser.getPDDocument());
		parser.getPDDocument().close();
		return result;
	}

}

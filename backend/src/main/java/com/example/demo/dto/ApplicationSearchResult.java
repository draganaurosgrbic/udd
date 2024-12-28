package com.example.demo.dto;

import java.util.List;
import java.util.Map;

import com.example.demo.model.ApplicationIndexUnit;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ApplicationSearchResult {

	private String adTitle;
	private String firstName;
	private String lastName;
	private String email;
	private String address;
	private String education;
	private Integer educationLevel;
	private String cvLocation;
	private String letterLocation;
	private String cvText;
	private String letterText;

	public ApplicationSearchResult(ApplicationIndexUnit indexUnit, Map<String, List<String>> highlights) {
		adTitle = indexUnit.getAdTitle();
		firstName = highlights.getOrDefault("firstName", List.of(indexUnit.getFirstName())).get(0);
		lastName = highlights.getOrDefault("lastName", List.of(indexUnit.getLastName())).get(0);
		email = indexUnit.getEmail();
		address = indexUnit.getAddress();
		education = highlights.getOrDefault("education", List.of(indexUnit.getEducation())).get(0);
		educationLevel = indexUnit.getEducationLevel();
		cvLocation = indexUnit.getCvLocation();
		letterLocation = indexUnit.getLetterLocation();

		if (highlights.get("cvText") != null) {
			StringBuilder cvBuilder = new StringBuilder();
			highlights.get("cvText").forEach(fragment -> cvBuilder.append(fragment + "..."));
			cvText = cvBuilder.toString();
			cvText = cvText.substring(0, Math.min(cvText.length(), 1000));
		} else {
			cvText = indexUnit.getCvText().substring(0, Math.min(indexUnit.getCvText().length(), 300)) + "...";
		}

		if (highlights.get("letterText") != null) {
			StringBuilder letterBuilder = new StringBuilder();
			highlights.get("letterText").forEach(fragment -> letterBuilder.append(fragment + "..."));
			letterText = letterBuilder.toString();
			letterText = letterText.substring(0, Math.min(letterText.length(), 1000));
		} else {
			letterText = indexUnit.getLetterText().substring(0, Math.min(indexUnit.getLetterText().length(), 300))
					+ "...";
		}
	}

}

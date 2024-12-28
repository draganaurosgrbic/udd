package com.example.demo.mapper;

import org.springframework.data.elasticsearch.core.geo.GeoPoint;
import org.springframework.stereotype.Service;

import com.example.demo.dto.ApplicationUpload;
import com.example.demo.model.Application;
import com.example.demo.model.ApplicationIndexUnit;
import com.example.demo.repository.AdvertisementRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ApplicationMapper {

	private final AdvertisementRepository repo;

	public Application map(ApplicationUpload upload) {
		Application model = new Application();
		model.setAdvertisement(repo.findById(upload.getAdvertisementId()).get());
		model.setFirstName(upload.getFirstName());
		model.setLastName(upload.getLastName());
		model.setEmail(upload.getEmail());
		model.setAddress(upload.getAddress());
		model.setEducation(upload.getEducation());
		model.setEducationLevel(upload.getEducationLevel());
		model.setLat(upload.getLat());
		model.setLng(upload.getLng());
		return model;
	}

	public ApplicationIndexUnit mapToIndexUnit(ApplicationUpload upload) {
		ApplicationIndexUnit indexUnit = new ApplicationIndexUnit();
		indexUnit.setAdTitle(repo.findById(upload.getAdvertisementId()).get().getTitle());
		indexUnit.setFirstName(upload.getFirstName());
		indexUnit.setLastName(upload.getLastName());
		indexUnit.setEmail(upload.getEmail());
		indexUnit.setAddress(upload.getAddress());
		indexUnit.setEducation(upload.getEducation());
		indexUnit.setEducationLevel(upload.getEducationLevel());
		indexUnit.setLocation(new GeoPoint(upload.getLat(), upload.getLng()));
		return indexUnit;
	}

}

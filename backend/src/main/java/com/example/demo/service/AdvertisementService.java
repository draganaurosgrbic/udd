package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.Advertisement;
import com.example.demo.repository.AdvertisementRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class AdvertisementService {

	private final AdvertisementRepository repo;

	public List<Advertisement> read() {
		return repo.findAll();
	}

}

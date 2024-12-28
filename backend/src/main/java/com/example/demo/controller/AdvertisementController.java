package com.example.demo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Advertisement;
import com.example.demo.service.AdvertisementService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/advertisements")
@PreAuthorize("hasAuthority('candidate')")
public class AdvertisementController {

	private final AdvertisementService service;

	@GetMapping
	public ResponseEntity<List<Advertisement>> read() {
		return ResponseEntity.ok(service.read());
	}

}

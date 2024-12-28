package com.example.demo.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ApplicationGeoSearch {

	@NotNull(message = "Lat cannot be null")
	private Double lat;

	@NotNull(message = "Lng cannot be null")
	private Double lng;

	@NotNull(message = "Distance cannot be null")
	private Double distance;

	@NotBlank(message = "Unit cannot be blank")
	private String unit;

}

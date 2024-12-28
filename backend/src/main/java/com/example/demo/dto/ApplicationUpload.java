package com.example.demo.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ApplicationUpload {

	@NotNull(message = "Advertisement ID cannot be null")
	private Long advertisementId;

	@NotBlank(message = "First name cannot be blank")
	private String firstName;

	@NotBlank(message = "Last name cannot be blank")
	private String lastName;

	@NotBlank(message = "Email cannot be blank")
	private String email;

	@NotBlank(message = "Address cannot be blank")
	private String address;

	@NotBlank(message = "Education cannot be blank")
	private String education;

	@NotNull(message = "Education level cannot be null")
	private Integer educationLevel;

	@NotNull(message = "Lat cannot be null")
	private Double lat;

	@NotNull(message = "Lng cannot be null")
	private Double lng;

	@NotNull(message = "CV file cannot be null")
	private MultipartFile cvFile;

	@NotNull(message = "Letter file cannot be null")
	private MultipartFile letterFile;

}

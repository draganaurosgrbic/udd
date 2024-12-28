package com.example.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.GeoPointField;
import org.springframework.data.elasticsearch.annotations.Setting;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Document(indexName = "library")
@Setting(settingPath = "settings.json")
public class ApplicationIndexUnit {

	@Field(type = FieldType.Text, index = false, store = true)
	private String adTitle;

	@Field(type = FieldType.Text, index = true, store = true)
	private String firstName;

	@Field(type = FieldType.Text, index = true, store = true)
	private String lastName;

	@Field(type = FieldType.Text, index = false, store = true)
	private String email;

	@Field(type = FieldType.Text, index = false, store = true)
	private String address;

	@Field(type = FieldType.Text, index = true, store = true)
	private String education;

	@Field(type = FieldType.Integer, index = true, store = true)
	private Integer educationLevel;

	@Id
	@Field(type = FieldType.Text, index = false, store = true)
	private String cvLocation;

	@Field(type = FieldType.Text, index = false, store = true)
	private String letterLocation;

	@Field(type = FieldType.Text, index = true, store = true)
	private String cvText;

	@Field(type = FieldType.Text, index = true, store = true)
	private String letterText;

	@GeoPointField
	private GeoPoint location;

}

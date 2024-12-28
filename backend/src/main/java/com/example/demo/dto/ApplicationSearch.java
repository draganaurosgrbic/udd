package com.example.demo.dto;

import java.util.List;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ApplicationSearch {

	@NotNull(message = "Queries cannot be null")
	private List<SimpleQuery> queries;

}

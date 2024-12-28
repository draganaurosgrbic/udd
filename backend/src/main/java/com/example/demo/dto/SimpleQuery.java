package com.example.demo.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class SimpleQuery {

	public enum Operator {
		AND, OR
	}

	@NotBlank(message = "Field cannot be blank")
	private String field;
	private String value;
	private Integer startValue;
	private Integer endValue;
	@NotNull(message = "Operator cannot be null")
	private Operator operator;
	@NotNull(message = "Not cannot be null")
	private Boolean not;

}

package com.sun.basic.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostRequestBodyDto {
	@NotBlank
	private String name;
	
	@NotNull
	private Integer age;
}

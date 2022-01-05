package com.example.demo.common;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ApiException {
	private int status;
	private String message;
	private String description;

	@Builder
	public ApiException(ApiCommonCode status, String message, String description) {
		this.status = status.getCode();
		this.message = message;
		this.description = description;
	}
}
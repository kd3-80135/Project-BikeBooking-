package com.bike.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseDTO {
	private LocalDateTime timeStamp;
	private String message;
	public ResponseDTO(String message) {
		this.timeStamp=LocalDateTime.now();
		this.message=message;
	}
}
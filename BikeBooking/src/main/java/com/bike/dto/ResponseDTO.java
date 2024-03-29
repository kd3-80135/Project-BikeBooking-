package com.bike.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ResponseDTO {
	private LocalDateTime timeStamp;
	private String message;
	public ResponseDTO(String message) {
		this.timeStamp=LocalDateTime.now();
		this.message=message;
	}
}
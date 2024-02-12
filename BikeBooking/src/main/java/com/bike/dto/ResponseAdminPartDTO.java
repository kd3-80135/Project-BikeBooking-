package com.bike.dto;

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
public class ResponseAdminPartDTO {

	private long id;
	
	private String name;
	
	private int quantity;
	
	private boolean approveStatus;
	
	private boolean deleteStatus;
	
	
}
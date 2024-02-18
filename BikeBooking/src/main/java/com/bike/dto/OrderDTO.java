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
public class OrderDTO {
	
	private String firstName;
	
	private String lastName;
	
	private Long orderId;
	
	private LocalDateTime placedAt;
	
	private LocalDateTime deliveredAt;
	
	private LocalDateTime cancelledAt;
	
	public OrderDTO (Long orderId, LocalDateTime placedAt, LocalDateTime deliveredAt, LocalDateTime cancelledAt) {
		this.orderId = orderId;
		this.placedAt = placedAt;
		this.deliveredAt = deliveredAt;
		this.cancelledAt = cancelledAt;
	}
	
}

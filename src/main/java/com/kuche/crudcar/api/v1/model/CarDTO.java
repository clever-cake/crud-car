package com.kuche.crudcar.api.v1.model;

import java.time.ZonedDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CarDTO {

	private Long id;
	private String brand;
	private String licensePlate;
	private String manufacturer;
	private String operationCity;
	private Status status;
	private ZonedDateTime createdAt;
	private ZonedDateTime lastUpdatedAt;

}

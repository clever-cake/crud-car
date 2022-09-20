package com.kuche.crudcar.api.v1.model;

import lombok.Data;

@Data
public class CarDTO {

	private String id;
	private String brand;
	private String licensePlate;
	private String manufacturer;
	private String operationCity;
	private String status;
	private String createdAt;
	private String lastUpdatedAt;

}

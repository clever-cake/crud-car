package com.kuche.crudcar.persistance.model;

import com.kuche.crudcar.api.v1.model.CarDTO;
import org.springframework.stereotype.Component;

@Component
public class ModelConverter {

	public PersistedCar mapCar(CarDTO car) {
		return PersistedCar.builder()
				.id(car.getId())
				.brand(car.getBrand())
				.createdAt(car.getCreatedAt())
				.lastUpdatedAt(car.getLastUpdatedAt())
				.manufacturer(car.getManufacturer())
				.operationCity(car.getOperationCity())
				.licensePlate(car.getLicensePlate())
				.status(car.getStatus())
				.build();
	}

	public CarDTO mapPersistedCar(PersistedCar persistedCar) {
		return CarDTO.builder()
				.id(persistedCar.getId())
				.brand(persistedCar.getBrand())
				.manufacturer(persistedCar.getManufacturer())
				.status(persistedCar.getStatus())
				.operationCity(persistedCar.getOperationCity())
				.licensePlate(persistedCar.getLicensePlate())
				.lastUpdatedAt(persistedCar.getLastUpdatedAt())
				.createdAt(persistedCar.getCreatedAt())
				.build();
	}

}

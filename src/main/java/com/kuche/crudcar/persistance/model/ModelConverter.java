package com.kuche.crudcar.persistance.model;

import com.kuche.crudcar.api.v1.model.CarDTO;
import com.kuche.crudcar.api.v1.model.Status;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

/**
 * The class is a simple approach to decouple the {@link CarDTO} object,
 * which is used for the json http api, from the entity class {@link PersistedCar}.
 *
 * In a real world scenario  this is a use case for <a href="https://mapstruct.org/">mapstruct</a>
 */
@Component
public class ModelConverter {

	public PersistedCar mapCar(CarDTO car) {
		PersistedCar.PersistedCarBuilder builder = PersistedCar.builder()
				.id(car.getId())
				.brand(car.getBrand())
				.createdAt(car.getCreatedAt())
				.lastUpdatedAt(car.getLastUpdatedAt())
				.manufacturer(car.getManufacturer())
				.operationCity(car.getOperationCity())
				.licensePlate(car.getLicensePlate());

		if (car.getStatus() != null) {
			builder.status(car.getStatus().getKey());
		}


		return builder
				.build();
	}

	public CarDTO mapPersistedCar(PersistedCar persistedCar) {
		CarDTO.CarDTOBuilder builder = CarDTO.builder()
				.id(persistedCar.getId())
				.brand(persistedCar.getBrand())
				.manufacturer(persistedCar.getManufacturer())
				.operationCity(persistedCar.getOperationCity())
				.licensePlate(persistedCar.getLicensePlate())
				.lastUpdatedAt(persistedCar.getLastUpdatedAt())
				.createdAt(persistedCar.getCreatedAt());

		if (StringUtils.isNotEmpty(persistedCar.getStatus())) {
			builder.status(Status.byKey(persistedCar.getStatus()));
		}

		return builder
				.build();
	}

}

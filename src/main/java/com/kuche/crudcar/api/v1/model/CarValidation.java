package com.kuche.crudcar.api.v1.model;

import com.kuche.crudcar.persistance.CarPersistence;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CarValidation {
	private final CarPersistence persistence;

	public void validateCar(CarDTO carDTO) {
		validatesThatTheUpdateIsBasedOnTheLastKnownVersion(carDTO);
		validateLicensePlate(carDTO);
		validateThatTheMetaDataIsNotSetOnNewCars(carDTO);

		// a couple of more complicated validations
	}

	private void validateThatTheMetaDataIsNotSetOnNewCars(CarDTO carDTO) {
		boolean isNewCar = carDTO.getId() == null;

		if (!isNewCar) {
			return;
		}

		if (carDTO.getLastUpdatedAt() != null || carDTO.getCreatedAt() != null) {
			throw new ValidationException("The metadata properties lastUpdatedAt and createdAt can only be set internally");
		}
	}

	private void validateLicensePlate(CarDTO carDTO) {
		if (StringUtils.isEmpty(carDTO.getLicensePlate())) {
			return;
		}

		String licensePlateRegex = "[A-ZÖÜÄ]{1,3}-[A-ZÖÜÄ]{1,2}-[1-9]{1}[0-9]{1,3}";

		if (!carDTO.getLicensePlate().matches(licensePlateRegex)) {
			throw new ValidationException("The given license plate is not valid");
		}
	}

	private void validatesThatTheUpdateIsBasedOnTheLastKnownVersion(CarDTO carDTO) {
		boolean hasAnId = carDTO.getId() != null;
		if (hasAnId) {
			Optional<CarDTO> updatedEntity = persistence.retrieveCarById(carDTO.getId());
			if (updatedEntity.isPresent()) {
				CarDTO entityToUpdate = updatedEntity.get();
				if (entityToUpdate.getLastUpdatedAt() != null && !entityToUpdate.getLastUpdatedAt().equals(carDTO.getLastUpdatedAt())) {
					throw new ValidationException("The car to be updated does not match the last known representation of that car");
				}
			}
		}
	}

}


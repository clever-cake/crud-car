package com.kuche.crudcar.api.v1;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.when;

import com.kuche.crudcar.api.v1.CarValidation;
import com.kuche.crudcar.api.v1.model.CarDTO;
import com.kuche.crudcar.api.v1.model.ValidationException;
import com.kuche.crudcar.persistance.CarPersistence;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CarValidationTest {

	@Mock
	private CarPersistence carPersistence;

	@InjectMocks
	private CarValidation carValidation;

	@ParameterizedTest
	@ValueSource(strings = {"notAValidLicensePlate", "aa", "  ", "aa---"})
	void identifyAnInvalidLicensePlate(String invalidLicensePlate) {
		CarDTO carToValidate = CarDTO.builder()
				.licensePlate(invalidLicensePlate)
				.build();

		assertThatExceptionOfType(ValidationException.class)
				.isThrownBy(() -> carValidation.validateCar(carToValidate));
	}

	@Test
	void identifyAValidLicensePlate() {
		CarDTO carToValidate = CarDTO.builder()
				.licensePlate("MR-TT-2442")
				.build();

		carValidation.validateCar(carToValidate);
	}

	@Test
	void updatedCarMustBeBasedOnLastKnownVersion() {
		long id = 1l;

		CarDTO dummyCar = CarDTO.builder()
				.id(id)
				.lastUpdatedAt(ZonedDateTime.now())
				.build();

		when(carPersistence.retrieveCarById(id)).thenReturn(Optional.of(dummyCar));

		CarDTO invalidCar = CarDTO.builder()
				.id(id)
				.lastUpdatedAt(ZonedDateTime.ofInstant(Instant.ofEpochMilli(100),
						ZoneId.systemDefault()))
				.build();

		assertThatExceptionOfType(ValidationException.class)
				.isThrownBy(() -> carValidation.validateCar(invalidCar));

	}

	@Test
	void aNewCarMayNotHaveACreationAndLastModificationDate() {
		CarDTO invalidCar = CarDTO.builder()
				.lastUpdatedAt(ZonedDateTime.ofInstant(Instant.ofEpochMilli(100),
						ZoneId.systemDefault()))
				.build();

		assertThatExceptionOfType(ValidationException.class)
				.isThrownBy(() -> carValidation.validateCar(invalidCar));

	}
}
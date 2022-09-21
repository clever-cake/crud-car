package com.kuche.crudcar.api.v1;

import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.kuche.crudcar.api.v1.model.CarDTO;
import com.kuche.crudcar.persistance.CarPersistence;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CarCrudControllerTest {
	@Mock
	private CarValidation validator;
	@Mock
	private CarPersistence carPersistence;
	@Mock
	private MetadataSetter metadataSetter;

	@InjectMocks
	private CarCrudController carCrudController;

	@Test
	void updateCar() {
		CarDTO carToUpdate = CarDTO.builder().build();
		carCrudController.updateCar(carToUpdate);

		InOrder orderedVerify = inOrder(validator, metadataSetter, carPersistence);

		orderedVerify.verify(validator, times(1)).validateCar(carToUpdate);
		orderedVerify.verify(metadataSetter, times(1)).updateMetaDataProperties(carToUpdate);
		orderedVerify.verify(carPersistence, times(1)).saveCar(carToUpdate);
	}

	@Test
	void retrieveCarById() {
		long id = 1l;

		carCrudController.retrieveCarById(id);
		verify(carPersistence, times(1)).retrieveCarById(id);
	}
}
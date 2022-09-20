package com.kuche.crudcar.persistance;

import com.kuche.crudcar.api.v1.model.CarDTO;
import com.kuche.crudcar.persistance.model.ModelConverter;
import com.kuche.crudcar.persistance.model.PersistedCar;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CarPersistence {

	private final CarRepository carRepository;
	private final ModelConverter modelConverter;


	public CarDTO saveCar(CarDTO carDTO) {
		PersistedCar savedEntity = carRepository.save(modelConverter.mapCar(carDTO));
		return modelConverter.mapPersistedCar(savedEntity);
	}


	public Optional<CarDTO> retrieveCarById(Long id) {
		return carRepository.findById(id).map(modelConverter::mapPersistedCar);
	}
}

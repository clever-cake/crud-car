package com.kuche.crudcar.api.v1;

import com.kuche.crudcar.api.v1.model.CarDTO;
import com.kuche.crudcar.persistance.CarPersistence;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("v1")
@AllArgsConstructor
@Slf4j
public class CarCrudController {

	private final CarValidation validator;
	private final CarPersistence carPersistence;

	private final MetadataSetter metadataSetter;


	@PostMapping("/car")
	public ResponseEntity<CarDTO> updateCar(@RequestBody CarDTO carDTO) {
		log.info("Update a car {}", carDTO);

		validator.validateCar(carDTO);
		metadataSetter.updateMetaDataProperties(carDTO);

		return ResponseEntity.ok(carPersistence.saveCar(carDTO));
	}

	@GetMapping("/car/{id}")
	public ResponseEntity<CarDTO> retrieveCarById(@PathVariable Long id) {

		return carPersistence.retrieveCarById(id)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}

}

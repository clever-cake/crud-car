package com.kuche.crudcar.api.v1;

import com.kuche.crudcar.api.v1.model.CarDTO;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("v1")
@Slf4j
public class CarCrudController {

	@PostMapping("/car")
	public void updateCar(CarDTO carDTO) {
		log.info("Update a car {}", carDTO);

	}

}

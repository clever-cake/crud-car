package com.kuche.crudcar;

import static org.assertj.core.api.Assertions.assertThat;

import com.kuche.crudcar.api.v1.CarCrudController;
import com.kuche.crudcar.api.v1.model.CarDTO;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest
@Testcontainers
@ContextConfiguration(initializers = CrudCarApplicationTests.TestEnvInitializer.class)
class CrudCarApplicationTests {

	@Container
	private static final PostgreSQLContainer postgres = new PostgreSQLContainer("postgres:14.2-alpine")
			.withDatabaseName("postgres")
			.withUsername("postgres")
			.withPassword("postgres");

	static class TestEnvInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

		@Override
		public void initialize(ConfigurableApplicationContext applicationContext) {
			TestPropertyValues values = TestPropertyValues.of(
					"spring.datasource.url=" + postgres.getJdbcUrl(),
					"spring.datasource.password=" + postgres.getPassword(),
					"spring.datasource.username=" + postgres.getUsername()
			);
			values.applyTo(applicationContext);
		}
	}


	@Autowired
	private CarCrudController carCrudController;

	@Test
	void saveAndRetrieveACar() {
		String licensePlate = "MR-TT-2442";
		ResponseEntity<CarDTO> updatedCar = carCrudController.updateCar(CarDTO.builder()
				.brand("brand")
				.licensePlate(licensePlate)
				.operationCity("Marburg")
				.build());

		assertThat(updatedCar.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(updatedCar.getBody()).isNotNull();

		ResponseEntity<CarDTO> retrieveCarById = carCrudController.retrieveCarById(updatedCar.getBody()
				.getId());

		assertThat(retrieveCarById.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(retrieveCarById.getBody())
				.isNotNull()
				.satisfies(carDTO -> StringUtils.equals(carDTO.getLicensePlate(), licensePlate));
	}

}

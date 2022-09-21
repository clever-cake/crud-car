package com.kuche.crudcar.api.v1.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.ZonedDateTime;
import org.junit.jupiter.api.Test;

class MetadataSetterTest {

	@Test
	void updateMetaDataProperties() {
		MetadataSetter uut = new MetadataSetter();

		CarDTO carDto = CarDTO.builder()
				.build();

		uut.updateMetaDataProperties(carDto);

		assertThat(carDto.getLastUpdatedAt()).isNotNull();
		assertThat(carDto.getCreatedAt()).isNotNull();
	}

	@Test
	void shouldNotOverrideTheCreatedAtDate() {
		MetadataSetter uut = new MetadataSetter();

		ZonedDateTime formerCreationTime = ZonedDateTime.now();

		CarDTO carDto = CarDTO.builder()
				.createdAt(formerCreationTime)
				.build();

		uut.updateMetaDataProperties(carDto);

		assertThat(carDto.getLastUpdatedAt()).isNotNull();
		assertThat(carDto.getCreatedAt())
				.isNotNull()
				.isEqualTo(formerCreationTime);
	}
}
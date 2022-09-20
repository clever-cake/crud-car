package com.kuche.crudcar.api.v1.model;

import java.time.ZonedDateTime;
import org.springframework.stereotype.Component;

@Component
public class MetadataSetter {

	public void updateMetaDataProperties(CarDTO carDTO) {
		ZonedDateTime now = ZonedDateTime.now();

		if (carDTO.getCreatedAt() == null) {
			carDTO.setCreatedAt(now);
		}

		carDTO.setLastUpdatedAt(now);
	}

}

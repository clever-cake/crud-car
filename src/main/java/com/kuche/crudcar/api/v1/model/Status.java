package com.kuche.crudcar.api.v1.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import java.util.Arrays;

public enum Status {
	AVAILABLE("available"), IN_MAINTENANCE("in_maintenance"), OUT_OF_SERVICE("out_of_service");

	private final String key;

	Status(String key) {
		this.key = key;
	}

	@JsonValue
	public String getKey() {
		return key;
	}

	@JsonCreator
	public static Status byKey(String key) {
		return Arrays.stream(values())
				.filter(status -> status.getKey().equals(key))
				.findFirst()
				.orElseThrow(() -> new IllegalArgumentException(key));

	}
}

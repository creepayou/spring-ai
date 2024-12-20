package com.spring_ai.test.cfg;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;

import com.spring_ai.test.models.HealthStatus;
import com.spring_ai.test.models.Patient;

@Configuration
public class MistralConfig {

	public static final Map<Patient, List<HealthStatus>> HEALTH_DATA = Map.of(
			new Patient("P001"), List.of(
					new HealthStatus("Healthy", LocalDateTime.of(2024, 12, 20, 9, 0)),
					new HealthStatus("Has cough", LocalDateTime.of(2024, 12, 20, 20, 0))),
			new Patient("P002"), List.of(
					new HealthStatus("Healthy", LocalDateTime.of(2024, 12, 20, 8, 0)),
					new HealthStatus("Has increased blood pressure", LocalDateTime.of(2024, 12, 20, 20, 0))));

	@Bean
	@Description("Get patient health status")
	public Function<Patient, List<HealthStatus>> retrievePatientHealthStatus() {
		return patient -> HEALTH_DATA.getOrDefault(patient, List.of());
	}

}

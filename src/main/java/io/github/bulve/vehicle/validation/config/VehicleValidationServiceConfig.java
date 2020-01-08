package io.github.bulve.vehicle.validation.config;

import io.github.bulve.vehicle.validation.VehicleValidationService;
import io.github.bulve.vehicle.validation.VehicleValidationServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class VehicleValidationServiceConfig {

    @Bean
    public VehicleValidationService vehicleValidationService(){
        return new VehicleValidationServiceImpl();
    }
}

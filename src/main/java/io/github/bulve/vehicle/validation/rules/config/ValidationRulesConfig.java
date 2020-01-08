package io.github.bulve.vehicle.validation.rules.config;

import io.github.bulve.vehicle.validation.VehicleValidationRule;
import io.github.bulve.vehicle.validation.rules.VehicleAgeRule;
import io.github.bulve.vehicle.validation.rules.VehicleMakeRule;
import io.github.bulve.vehicle.validation.rules.VehicleModelRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Supplies {@link VehicleValidationRule} beans.
 *
 * @author alex
 */
@Configuration
public class ValidationRulesConfig {

    @Bean
    public VehicleValidationRule vehicleAgeRule() {
        return new VehicleAgeRule();
    }

    @Bean
    public VehicleValidationRule vehicleMakeRule() {
        return new VehicleMakeRule();
    }

    @Bean
    public VehicleValidationRule vehicleModelRule() {
        return new VehicleModelRule();
    }
}

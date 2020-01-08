package io.github.bulve.vehicle.validation.rules;

import io.github.bulve.vehicle.model.Vehicle;
import io.github.bulve.vehicle.validation.RuleValidationResult;
import io.github.bulve.vehicle.validation.VehicleValidationRule;

/**
 * Validates {@link Vehicle} to have {@link Vehicle#getMake()} not empty and not null.
 * If not valid will response with {@link RuleValidationResult#notValid(String)}
 * other wise {@link RuleValidationResult#valid()}.
 *
 * @author alex
 */
public class VehicleMakeRule implements VehicleValidationRule {

    @Override
    public RuleValidationResult validateVehicle(Vehicle vehicle) {
        if(vehicle.getMake() == null || vehicle.getMake().isEmpty()) {
            return RuleValidationResult.notValid("Vehicle 'Make' is empty or omitted");
        }
        return RuleValidationResult.valid();
    }
}

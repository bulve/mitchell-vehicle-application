package io.github.bulve.vehicle.validation.rules;

import io.github.bulve.vehicle.model.Vehicle;
import io.github.bulve.vehicle.validation.RuleValidationResult;
import io.github.bulve.vehicle.validation.VehicleValidationRule;

/**
 * Validates {@link Vehicle} to have {@link Vehicle#getModel()} ()} not empty and not null.
 * If not valid will response with {@link RuleValidationResult#notValid(String)}
 * other wise {@link RuleValidationResult#valid()}.
 *
 * @author alex
 */
public class VehicleModelRule implements VehicleValidationRule {

    @Override
    public RuleValidationResult validateVehicle(Vehicle vehicle) {
        if(vehicle.getModel() == null || vehicle.getModel().isEmpty()) {
            return RuleValidationResult.notValid("Vehicle model is empty or omitted");
        }
        return RuleValidationResult.valid();
    }
}

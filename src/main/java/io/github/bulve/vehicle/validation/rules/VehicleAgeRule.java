package io.github.bulve.vehicle.validation.rules;

import io.github.bulve.vehicle.model.Vehicle;
import io.github.bulve.vehicle.validation.RuleValidationResult;
import io.github.bulve.vehicle.validation.VehicleValidationRule;

/**
 * Validates {@link Vehicle} to have build year between {@link this#MAXIMUM_AGE} and {@link this#MAXIMUM_AGE}.
 * If not in the range will response with {@link RuleValidationResult#notValid(String)}
 * other wise {@link RuleValidationResult#valid()}.
 *
 * @author alex
 */
public class VehicleAgeRule implements VehicleValidationRule {

    private static final Integer MINIMUM_AGE = 1950;
    private static final Integer MAXIMUM_AGE = 2050;

    @Override
    public RuleValidationResult validateVehicle(Vehicle vehicle) {
        if(vehicle.getYear().compareTo(MINIMUM_AGE) < 0 || vehicle.getYear().compareTo(MAXIMUM_AGE) > 0){
            String message = String.format("Vehicle year '%s' does not meet requirement: '%s < year < %s",
                    vehicle.getYear(), MINIMUM_AGE, MAXIMUM_AGE);
            return RuleValidationResult.notValid(message);
        }
        return RuleValidationResult.valid();
    }
}

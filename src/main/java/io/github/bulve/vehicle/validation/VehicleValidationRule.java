package io.github.bulve.vehicle.validation;

import io.github.bulve.vehicle.model.Vehicle;

/**
 * Single rule used to validate {@link Vehicle}.
 *
 * @author alex
 */
public interface VehicleValidationRule {

    /**
     * Validates supplied {@link Vehicle}.
     * If Vehicle not valid must return {@link RuleValidationResult#notValid(String)}
     * other wise {@link RuleValidationResult#valid()}
     *
     * @param vehicle to validate.
     * @return
     */
    RuleValidationResult validateVehicle(Vehicle vehicle);
}

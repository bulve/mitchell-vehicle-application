package io.github.bulve.vehicle.validation;

import io.github.bulve.vehicle.model.Vehicle;

/**
 * Validation service for {@link Vehicle} used in {@link io.github.bulve.vehicle.VehicleController}.
 *
 * @author alex
 */
public interface VehicleValidationService {

    /**
     * Validates provided {@link Vehicle}
     * @param vehicle to validate.
     * @return result with validation failure messages if any occurred in validation.
     */
    VehicleValidationResult validate(Vehicle vehicle);

}

package io.github.bulve.vehicle.validation;

import io.github.bulve.vehicle.model.Vehicle;

import java.util.Collection;

/**
 * {@link VehicleValidationService} validation response.
 *
 * @author alex
 */
public class VehicleValidationResult {

    /**
     * Describes if {@link Vehicle} valid by {@link VehicleValidationService}.
     */
    private boolean valid;

    /**
     * Contains all validation failure message occurred in validation of {@link Vehicle}.
     */
    private Collection<String> validationFailureMessages;


    public VehicleValidationResult(Collection<String> validationFailureMessages) {
        this.valid = validationFailureMessages.size() == 0;
        this.validationFailureMessages = validationFailureMessages;
    }

    public boolean isValid() {
        return valid;
    }

    public Collection<String> getValidationFailureMessages() {
        return validationFailureMessages;
    }
}

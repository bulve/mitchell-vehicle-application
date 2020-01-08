package io.github.bulve.vehicle.validation;

import io.github.bulve.vehicle.model.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author alex
 */
public class VehicleValidationServiceImpl implements VehicleValidationService {

    @Autowired
    private Collection<VehicleValidationRule> validationRules = Collections.emptyList();

    @Override
    public VehicleValidationResult validate(Vehicle vehicle) {
        List<String> validationErrors = validationRules.stream()
                .map(rule -> rule.validateVehicle(vehicle))
                .filter(result -> !result.isValid())
                .map(RuleValidationResult::getValidationMessage)
                .collect(Collectors.toList());
        return new VehicleValidationResult(validationErrors);
    }
}

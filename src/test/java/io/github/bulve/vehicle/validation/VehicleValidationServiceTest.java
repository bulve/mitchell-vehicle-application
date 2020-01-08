package io.github.bulve.vehicle.validation;

import io.github.bulve.vehicle.model.Vehicle;
import io.github.bulve.vehicle.validation.config.VehicleValidationServiceConfig;
import io.github.bulve.vehicle.validation.rules.config.ValidationRulesConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

@ContextConfiguration(classes = {
        ValidationRulesConfig.class,
        VehicleValidationServiceConfig.class
})
@RunWith(SpringRunner.class)
public class VehicleValidationServiceTest {

    @Autowired
    private VehicleValidationService vehicleValidationService;

    @Test
    public void shouldValidateVehicle(){
        VehicleValidationResult result = vehicleValidationService.validate(mockVehicle());
        assertThat(result.isValid(), is(true));
    }

    @Test
    public void shouldFindVehicleNotValidBecauseOfTheYears(){
        Vehicle vehicleToValidate = mockVehicle();
        vehicleToValidate.setYear(1940);
        VehicleValidationResult result = vehicleValidationService.validate(vehicleToValidate);
        assertThat(result.isValid(), is(false));
        assertThat(result.getValidationFailureMessages(), hasSize(1));
        assertThat(result.getValidationFailureMessages().iterator().next(),
                is("Vehicle year '1940' does not meet requirement: '1950 < year < 2050"));
    }

    @Test
    public void shouldFindVehicleNotValidBecauseOfEmptyMake(){
        Vehicle vehicleToValidate = mockVehicle();
        vehicleToValidate.setMake("");
        VehicleValidationResult result = vehicleValidationService.validate(vehicleToValidate);
        assertThat(result.isValid(), is(false));
        assertThat(result.getValidationFailureMessages(), hasSize(1));
        assertThat(result.getValidationFailureMessages().iterator().next(),
                is("Vehicle 'Make' is empty or omitted"));
    }

    @Test
    public void shouldFindVehicleNotValidBecauseOfNullModel(){
        Vehicle vehicleToValidate = mockVehicle();
        vehicleToValidate.setModel(null);
        VehicleValidationResult result = vehicleValidationService.validate(vehicleToValidate);
        assertThat(result.isValid(), is(false));
        assertThat(result.getValidationFailureMessages(), hasSize(1));
        assertThat(result.getValidationFailureMessages().iterator().next(),
                is("Vehicle model is empty or omitted"));
    }


    private Vehicle mockVehicle() {
        Vehicle vehicleDTO = new Vehicle();
        vehicleDTO.setMake("mock make");
        vehicleDTO.setModel("mock model");
        vehicleDTO.setYear(2002);
        return vehicleDTO;
    }
}

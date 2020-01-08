package io.github.bulve.vehicle;

import io.github.bulve.vehicle.model.Vehicle;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;

public class VehicleServiceTest {

    @InjectMocks
    private VehicleService vehicleService = new VehicleServiceImpl();

    @Mock
    private VehicleRepository vehicleRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldGetAllVehicle() {
        when(vehicleRepository.findAllBySearch(null, null, null))
                .thenReturn(Arrays.asList(mockVehicle(), mockVehicle()));
        Collection<Vehicle> vehicles = vehicleService.getVehicles(null, null, null);
        assertThat(vehicles, hasSize(2));
    }

    private Vehicle mockVehicle() {
        Vehicle vehicleDTO = new Vehicle();
        vehicleDTO.setMake("mock make");
        vehicleDTO.setModel("mock model");
        vehicleDTO.setYear(2002);
        return vehicleDTO;
    }
}

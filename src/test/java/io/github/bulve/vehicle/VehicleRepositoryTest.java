package io.github.bulve.vehicle;

import io.github.bulve.vehicle.model.Vehicle;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@DataJpaTest
@RunWith(SpringRunner.class)
public class VehicleRepositoryTest {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Test
    public void shouldGetSavedVehicle() {
        Vehicle savedVehicle = vehicleRepository.save(mockVehicle());
        Vehicle vehicleById = vehicleRepository.findById(savedVehicle.getId()).get();

        assertThat(vehicleById.getId(), is(savedVehicle.getId()));
        assertThat(vehicleById.getYear(), is(savedVehicle.getYear()));
        assertThat(vehicleById.getMake(), is(savedVehicle.getMake()));
        assertThat(vehicleById.getModel(), is(savedVehicle.getModel()));
    }

    private Vehicle mockVehicle() {
        Vehicle vehicle = new Vehicle();
        vehicle.setMake("mock make");
        vehicle.setModel("mock model");
        vehicle.setYear(1975);
        return vehicle;
    }
}

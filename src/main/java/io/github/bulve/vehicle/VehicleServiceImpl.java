package io.github.bulve.vehicle;

import io.github.bulve.vehicle.model.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

/**
 * Implementation of {@link VehicleService} for persisting nad retrieving {@link Vehicle} entities.
 * Expect to have {@link VehicleRepository} defined as {@link org.springframework.context.annotation.Bean}
 *
 * @author alex
 */
@Service
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Override
    public Collection<Vehicle> getVehicles(String make, String model, Integer year) {
        return vehicleRepository.findAllBySearch(make, model, year);
    }

    @Override
    public Optional<Vehicle> getVehicle(Integer vehicleId) {
        return vehicleRepository.findById(vehicleId);
    }

    @Override
    public Vehicle saveVehicle(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    @Override
    public Vehicle updateVehicle(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    @Override
    public boolean deleteVehicle(Integer vehicleId) {
        return Optional.ofNullable(vehicleId)
                .flatMap(id -> vehicleRepository.findById(id))
                    .map(vehicle -> {
                    vehicleRepository.deleteById(vehicle.getId());
                    return true;
                })
                .orElse(false);
    }
}

package io.github.bulve.vehicle;

import io.github.bulve.vehicle.model.Vehicle;

import java.util.Collection;
import java.util.Optional;

/**
 * Service to persist and retrieve {@link Vehicle} entities.
 *
 * @author alex
 */
public interface VehicleService {

    /**
     * Get all Vehicle by provided search properties.
     * If no search properties provided, will return all Vehicle.
     *
     * @param make
     * @param model
     * @param year
     * @return found Vehicles.
     */
    Collection<Vehicle> getVehicles(String make, String model, Integer year);

    /**
     * Get vehicle by vehicleId.
     *
     * @param vehicleId to find Vehicle by.
     * @return optional od Vehicle.
     */
    Optional<Vehicle> getVehicle(Integer vehicleId);

    /**
     * Save provided Vehicle.
     *
     * @param vehicle to save.
     * @return saved vehicle.
     */
    Vehicle saveVehicle(Vehicle vehicle);

    /**
     * Update provided Vehicle.
     *
     * @param vehicle to update.
     * @return updated vehicle.
     */
    Vehicle updateVehicle(Vehicle vehicle);

    /**
     * Delete Vehicle by provided vehicleId.
     *
     * @param vehicleId
     * @return true if deleted, false if not deleted or found
     */
    boolean deleteVehicle(Integer vehicleId);
}

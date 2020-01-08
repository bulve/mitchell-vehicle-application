package io.github.bulve.vehicle.model.dto.mapper;

import io.github.bulve.vehicle.model.Vehicle;
import io.github.bulve.vehicle.model.dto.VehicleDTO;

/**
 * Mapper for {@link Vehicle} and {@link VehicleDTO}
 *
 * @author alex
 */
public class VehicleMapper {

    /**
     * Maps supplied {@link VehicleDTO} to {@link Vehicle}
     * All properties from vehicleDTO will be coped over to vehicle.
     *
     * @param vehicleDTO to map.
     * @return Vehicle.
     */
    public static Vehicle vehicleDTOToVehicle(VehicleDTO vehicleDTO) {
        Vehicle vehicle = new Vehicle();
        vehicle.setId(vehicleDTO.getId());
        vehicle.setMake(vehicleDTO.getMake());
        vehicle.setModel(vehicleDTO.getModel());
        vehicle.setYear(vehicleDTO.getYear());
        return vehicle;
    }

    /**
     * Maps supplied {@link Vehicle} to {@link VehicleDTO}
     * All properties from vehicle will be coped over to vehicleDTO.
     *
     * @param vehicle to map.
     * @return VehicleDTO.
     */
    public static VehicleDTO vehicleToVehicleDTO(Vehicle vehicle) {
        VehicleDTO dto = new VehicleDTO();
        dto.setId(vehicle.getId());
        dto.setMake(vehicle.getMake());
        dto.setModel(vehicle.getModel());
        dto.setYear(vehicle.getYear());
        return dto;
    }
}

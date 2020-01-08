package io.github.bulve.vehicle;

import io.github.bulve.vehicle.model.Vehicle;
import io.github.bulve.vehicle.model.dto.VehicleDTO;
import io.github.bulve.vehicle.model.dto.mapper.VehicleMapper;
import io.github.bulve.vehicle.validation.VehicleValidationResult;
import io.github.bulve.vehicle.validation.VehicleValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Application controller for manipulating {@link Vehicle} entities in {@link VehicleService}.
 * Validates {@link Vehicle} entities before
 * saving {@link this#saveVehicle(VehicleDTO)} and updating {@link this#updateVehicle(VehicleDTO)}.
 *
 * Requires qualified {@link VehicleService} and {@link VehicleValidationService} services as beans in classpath.
 *
 * @author alex
 */
@RestController
@RequestMapping("api/vehicles")
@CrossOrigin(origins = "*")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private VehicleValidationService vehicleValidationService;

    /**
     * Gets all {@link Vehicle} by if any provided search values: make, mode, years.
     *
     * @param make of Vehicle to search.
     * @param model of Vehicle to search.
     * @param year of Vehicle to search.
     * @return All found vehicles with {@link HttpStatus#OK}
     */
    @GetMapping()
    public ResponseEntity<Collection<VehicleDTO>> getVehicles(@PathParam("make") String make,
                                                              @PathParam("model") String model,
                                                              @PathParam("year") Integer year) {
        List<VehicleDTO> vehicleDTOS = vehicleService.getVehicles(make,model,year).stream()
                    .map(VehicleMapper::vehicleToVehicleDTO)
                    .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(vehicleDTOS);
    }

    /**
     * Gets {@link Vehicle} by id provided in path.
     *
     * @param vehicleId to find Vehicle by.
     * @return if Vehicle found, Vehicle with {@link org.springframework.http.HttpStatus#OK}
     * else {@link org.springframework.http.HttpStatus#BAD_REQUEST}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<VehicleDTO> getVehicles(@PathVariable("id") Integer vehicleId) {
        return vehicleService.getVehicle(vehicleId)
                .map(vehicle -> ResponseEntity
                        .status(HttpStatus.OK)
                        .body(VehicleMapper.vehicleToVehicleDTO(vehicle)))
                .orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
    }

    /**
     * Saves provided Vehicle.
     * Vehicle must be valid by {@link VehicleValidationService} to be saved.
     * Other wise response with {@link org.springframework.http.HttpStatus#BAD_REQUEST}.
     *
     * @param vehicleDTO
     * @return Saved Vehicle with {@link org.springframework.http.HttpStatus#CREATED}
     * or {@link org.springframework.http.HttpStatus#BAD_REQUEST} if not saved because not valid.
     */
    @PostMapping()
    public ResponseEntity<VehicleDTO> saveVehicle(@RequestBody VehicleDTO vehicleDTO) {
        Vehicle vehicle = VehicleMapper.vehicleDTOToVehicle(vehicleDTO);
        VehicleValidationResult vehicleValidationResult = vehicleValidationService.validate(vehicle);
        if(vehicleValidationResult.isValid()){
            vehicle = vehicleService.saveVehicle(vehicle);
            return ResponseEntity.status(HttpStatus.CREATED).body(VehicleMapper.vehicleToVehicleDTO(vehicle));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    /**
     * Update provided Vehicle.
     * Vehicle must be valid by {@link VehicleValidationService} to be updated.
     * Other wise response with {@link org.springframework.http.HttpStatus#BAD_REQUEST}.
     *
     * @param vehicleDTO
     * @return Updated Vehicle with {@link org.springframework.http.HttpStatus#OK}
     * or {@link org.springframework.http.HttpStatus#BAD_REQUEST} if not updated because not valid.
     */
    @PutMapping()
    public ResponseEntity<VehicleDTO> updateVehicle(@RequestBody VehicleDTO vehicleDTO) {
        Vehicle vehicle = VehicleMapper.vehicleDTOToVehicle(vehicleDTO);
        VehicleValidationResult vehicleValidationResult = vehicleValidationService.validate(vehicle);
        if(vehicleValidationResult.isValid()){
            vehicle = vehicleService.updateVehicle(vehicle);
            return ResponseEntity.status(HttpStatus.OK).body(VehicleMapper.vehicleToVehicleDTO(vehicle));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    /**
     * Delete Vehicle by provided vehicleId
     *
     * @param vehicleId
     * @return with {@link HttpStatus#OK} when Vehicle marked for deletion successfully
     * other wise {@link HttpStatus#BAD_REQUEST} when Vehicle not marked for deletion or not found by vehicleId.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity deleteVehicle(@PathVariable("id") Integer vehicleId) {
        return vehicleService.deleteVehicle(vehicleId)
                ? ResponseEntity.status(HttpStatus.OK).build()
                : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}

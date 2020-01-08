package io.github.bulve.vehicle;

import io.github.bulve.vehicle.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

/**
 * Jpa repository for persisting and retrieving {@link Vehicle} entities.
 *
 * @author alex
 */
@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {

    /**
     * Custom query search for Vehicles by provided properties if any.
     * When properties are {@code null} then all Vehicles will be returned.
     */
    @Query(value = "SELECT * FROM Vehicle " +
            "WHERE (?1 IS NULL OR make = ?1) " +
            "AND (?2 IS NULL OR model = ?2) " +
            "AND (?3 IS NULL OR year = ?3)",  nativeQuery = true)
    Collection<Vehicle> findAllBySearch(String make, String model, Integer year);
}

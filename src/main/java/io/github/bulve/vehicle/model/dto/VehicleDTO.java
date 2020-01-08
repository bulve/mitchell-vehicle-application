package io.github.bulve.vehicle.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Represents Vehicle entity used in {@link io.github.bulve.vehicle.VehicleController}
 *
 * @author alex
 */
public class VehicleDTO implements Serializable {

    /**
     * Uniquely identifies vehicle
     */
    private Integer id;

    /**
     * Vehicle build year.
     */
    private Integer year;

    /**
     * Represents Vehicle make.
     * EX. "Toyota", "Mazda", "BMW" and others...
     */
    private String make;

    /**
     * Represents Vehicle model.
     * EX. "Toyota" models "Corolla", "Celica", "Sienna" and others...
     */
    private String model;

    @JsonProperty("Id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("Id")
    public void setId(Integer id) {
        this.id = id;
    }

    @JsonProperty("Year")
    public Integer getYear() {
        return year;
    }

    @JsonProperty("Year")
    public void setYear(Integer year) {
        this.year = year;
    }

    @JsonProperty("Make")
    public String getMake() {
        return make;
    }

    @JsonProperty("Make")
    public void setMake(String make) {
        this.make = make;
    }

    @JsonProperty("Model")
    public String getModel() {
        return model;
    }

    @JsonProperty("Model")
    public void setModel(String model) {
        this.model = model;
    }
}

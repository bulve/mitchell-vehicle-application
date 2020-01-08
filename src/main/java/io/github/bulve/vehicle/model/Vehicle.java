package io.github.bulve.vehicle.model;

import org.hibernate.validator.constraints.Range;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Represents Vehicle as entity in a storage.
 *
 * @author alex
 */
@Entity
public class Vehicle implements Serializable {

    /**
     * Uniquely identifies vehicle in storage.
     */
    @Id
    @GeneratedValue
    private Integer id;

    /**
     * Vehicle build year.
     */
    @Range(min = 1950, max = 2050)
    private Integer year;

    /**
     * Represents Vehicle make.
     * EX. "Toyota", "Mazda", "BMW" and others...
     */
    @NotNull
    @NotEmpty
    private String make;

    /**
     * Represents Vehicle model.
     * EX. "Toyota" models "Corolla", "Celica", "Sienna" and others...
     */
    @NotNull
    @NotEmpty
    private String model;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}

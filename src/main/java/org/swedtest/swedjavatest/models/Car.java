package org.swedtest.swedjavatest.models;

import java.util.Date;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Car {
    @NotBlank
    private final String plateNumber;
    @NotBlank
    private final Float height;
    @NotBlank
    private final int weight;
    private Date parkingTime;

    public Car(@JsonProperty("platenumber") String plateNumber, @JsonProperty("height") Float height,
            @JsonProperty("weight") int weight) {
        this.plateNumber = plateNumber;
        this.height = height;
        this.weight = weight;
    }

    public String getPlateNumber() {
        return this.plateNumber;
    }

    public Float getHeight() {
        return this.height;
    }

    public int getWeight() {
        return this.weight;
    }

    public Date getParkingTime() {
        return this.parkingTime;
    }

    public void setParkingTime(Date parkingTime) {
        this.parkingTime = parkingTime;
    }

}

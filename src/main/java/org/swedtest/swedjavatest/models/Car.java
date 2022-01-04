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
    private Date parkingTimeStart;
    private int parkedToFloor;

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

    public Date getParkingTimeStart() {
        return this.parkingTimeStart;
    }

    public void setParkingTimeStart(Date parkingTimeStart) {
        this.parkingTimeStart = parkingTimeStart;
    }

    public int getParkedToFloor() {
        return this.parkedToFloor;
    }

    public void setParkedToFloor(int parkedToFloor) {
        this.parkedToFloor = parkedToFloor;
    }

}

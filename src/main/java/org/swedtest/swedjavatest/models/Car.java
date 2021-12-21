package org.swedtest.swedjavatest.models;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Car {
    @NotBlank
    private String plateNumber;
    @NotBlank
    private Float height;
    @NotBlank
    private int weight;


    public Car(@JsonProperty("platenumber") String plateNumber, @JsonProperty("height") Float height, @JsonProperty("weight") int weight) {
        this.plateNumber = plateNumber;
        this.height = height;
        this.weight = weight;
    }


    public String getPlateNumber() {
        return this.plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public Float getHeight() {
        return this.height;
    }

    public void setHeight(Float height) {
        this.height = height;
    }

    public int getWeight() {
        return this.weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }


}

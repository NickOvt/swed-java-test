package org.swedtest.swedjavatest.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;

@Entity
@Table
public class Car {
    @Id
    @NotBlank
    private final String plateNumber;
    @Positive
    @NotNull
    private final Float height;
    @Positive
    @NotNull
    private final int weight;
    @NotNull
    @PastOrPresent
    private Date parkingTimeStart;
    @Positive
    @NotNull
    private int parkedToFloor;

    @ManyToOne
    @JoinColumn(name = "floorNumber", nullable = false)
    private Floor floor;

    public Car(String plateNumber, Float height,
            int weight) {
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

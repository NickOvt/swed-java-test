package org.swedtest.swedjavatest.models;

import java.util.List;
import java.util.Random;

public class ParkingHouse {
    private final int amountOfFloors;
    private List<Floor> parkingFloors;
    private int currLastFloor = 0;

    public ParkingHouse(int amountOfFloors, List<Floor> parkingFloors) {
        this.amountOfFloors = amountOfFloors;
        this.parkingFloors = parkingFloors;
    }

    public int getAmountOfFloors() {
        return this.amountOfFloors;
    }

    public List<Floor> getParkingFloors() {
        return this.parkingFloors;
    }

    public void addParkingFloor(Float floorCeilingHeight, int floorTotalWeightCapacity) {
        this.parkingFloors.add(new Floor(currLastFloor + 1, floorCeilingHeight, floorTotalWeightCapacity, new Random().nextInt(100)));
        this.currLastFloor++;
    }
}

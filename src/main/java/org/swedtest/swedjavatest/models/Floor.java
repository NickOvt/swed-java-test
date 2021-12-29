package org.swedtest.swedjavatest.models;

public class Floor {
    private final int floorNumber;
    private final Float ceilingHeight;
    private final int totalWeightCapacity;
    private int leftWeightCapacity;
    private final int totalSpots;
    private int leftSpots;

    public Floor(int floorNumber, Float ceilingHeight, int totalWeightCapacity, int totalSpots) {
        this.floorNumber = floorNumber;
        this.ceilingHeight = ceilingHeight;
        this.totalWeightCapacity = totalWeightCapacity;
        this.leftWeightCapacity = totalWeightCapacity;
        this.totalSpots = totalSpots;
        this.leftSpots = totalSpots;
    }

    public Float getCeilingHeight() {
        return this.ceilingHeight;
    }

    public int getTotalWeightCapacity() {
        return this.totalWeightCapacity;
    }

    public int getFloorNumber() {
        return this.floorNumber;
    }

    public int getLeftWeightCapacity() {
        return this.leftWeightCapacity;
    }

    public void setLeftWeightCapacity(int parkedCarWeight) {
        this.leftWeightCapacity = this.leftWeightCapacity - parkedCarWeight;
    }

    public int getTotalSpots() {
        return this.totalSpots;
    }

    public int getLeftSpots() {
        return this.leftSpots;
    }

    public void setLeftSpots(int leftSpots) {
        this.leftSpots = leftSpots;
    }

}
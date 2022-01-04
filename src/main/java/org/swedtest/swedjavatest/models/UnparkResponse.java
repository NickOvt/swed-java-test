package org.swedtest.swedjavatest.models;

public class UnparkResponse {
    private final String carPlateNumber;
    private final Float parkingPrice;

    public UnparkResponse(String carPlateNumber, Float parkingPrice) {
        this.carPlateNumber = carPlateNumber;
        this.parkingPrice = parkingPrice;
    }

    public String getCarPlateNumber() {
        return this.carPlateNumber;
    }

    public Float getParkingPrice() {
        return this.parkingPrice;
    }

}

package org.swedtest.swedjavatest.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.stereotype.Repository;
import org.swedtest.swedjavatest.models.Car;
import org.swedtest.swedjavatest.models.Floor;

@Repository()
public class FakeParkingHouseDataAccessService implements ParkingHouseDao {
    private static List<Floor> parkingHouseFloors = new ArrayList<>();

    @Override
    public Optional<Floor> selectFloorById(int floorNumber) {
        return parkingHouseFloors.stream().filter(floor -> floor.getFloorNumber() == floorNumber).findFirst();
    }

    @Override
    public Floor parkCar(Floor floor, Car car) {
        Floor currFloor = floor;
        car.setParkedToFloor(currFloor.getFloorNumber());
        int indexOfFloorToUpdate = parkingHouseFloors.indexOf(currFloor);

        // currFloor.addParkedCarsPlateNumber(car.getPlateNumber());
        currFloor.setLeftSpots(currFloor.getLeftSpots() - 1);
        currFloor.setLeftWeightCapacity(car.getWeight());

        parkingHouseFloors.set(indexOfFloorToUpdate, currFloor);
        return currFloor;
    }

    @Override
    public void unparkCar(Floor floor, Car car) {
        Floor currFloor = floor;
        int indexOfFloorToUpdate = parkingHouseFloors.indexOf(currFloor);

        // currFloor.removePlateNumberFromParkedCarsPlateNumber(car.getPlateNumber());

        parkingHouseFloors.set(indexOfFloorToUpdate, currFloor);
    }

    @Override
    public List<Floor> getAllFloors() {
        return parkingHouseFloors;
    }

    @Override
    public void addParkingFloor(Floor floor) {
        parkingHouseFloors.add(floor);
    }

    @Override
    public List<Floor> initializeParkingHouse() {
        Random random = new Random();
        int amountOfFloors = random.nextInt(2, 20);
        for (int i = 0; i < amountOfFloors; i++) {
            this.addParkingFloor(
                    new Floor(i, random.nextFloat(1.0f, 3.0f), random.nextInt(100000, 1000000), random.nextInt(20, 100)));
        }
        return parkingHouseFloors;
    }

    @Override
    public Optional<List<String>> getAllCarPlateNumbersFromFloor(int floorId) {
        return selectFloorById(floorId).map(fl -> {
            // return fl.getParkedCarsPlateNumbers();
            throw new RuntimeException();
        });
    }
}

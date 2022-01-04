package org.swedtest.swedjavatest.dao;

import java.util.List;
import java.util.Optional;

import org.swedtest.swedjavatest.models.Car;
import org.swedtest.swedjavatest.models.Floor;

public interface ParkingHouseDao {
    Floor parkCar(Floor floor, Car car);

    void unparkCar(Floor floor, Car car);

    Optional<Floor> selectFloorById(int floorNumber);

    List<Floor> getAllFloors();

    void addParkingFloor(Floor floor);

    List<Floor> initializeParkingHouse();

    List<String> getAllCarPlateNumbersFromFloor(int floorId);
}

package org.swedtest.swedjavatest.dao;

import java.util.List;
import java.util.Optional;

import org.swedtest.swedjavatest.models.Car;
import org.swedtest.swedjavatest.models.Floor;

public interface ParkingHouseDao {
    boolean parkCar(Floor floor, Car car);

    Optional<Floor> selectFloorById(int floorNumber);

    List<Floor> getAllFloors();

    void addParkingFloor(Floor floor);

    List<Floor> initializeParkingHouse();
}

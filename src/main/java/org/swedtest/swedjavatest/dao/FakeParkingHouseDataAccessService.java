package org.swedtest.swedjavatest.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.swedtest.swedjavatest.models.Car;
import org.swedtest.swedjavatest.models.Floor;
import org.swedtest.swedjavatest.models.ParkingHouse;

public class FakeParkingHouseDataAccessService implements ParkingHouseDao {
    private List<Floor> parkingHouseFloors;

    @Override
    public Optional<Floor> selectFloorById(int floorNumber) {
        return parkingHouseFloors.stream().filter(floor -> floor.getFloorNumber() == floorNumber).findFirst();
    }

    @Override
    public boolean parkCar(Floor floor, Car car) {
        return true;
    }

    @Autowired
    public FakeParkingHouseDataAccessService(ParkingHouse parkingHouse) {
        this.parkingHouseFloors = parkingHouse.getParkingFloors();
    }
}

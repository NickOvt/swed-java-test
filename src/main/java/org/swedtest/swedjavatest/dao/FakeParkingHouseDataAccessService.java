package org.swedtest.swedjavatest.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.swedtest.swedjavatest.models.Car;
import org.swedtest.swedjavatest.models.Floor;
// import org.swedtest.swedjavatest.models.ParkingHouse;

@Repository("fakeparkinghousedao")
public class FakeParkingHouseDataAccessService implements ParkingHouseDao {
    private static List<Floor> parkingHouseFloors = new ArrayList<>();

    @Override
    public Optional<Floor> selectFloorById(int floorNumber) {
        return parkingHouseFloors.stream().filter(floor -> floor.getFloorNumber() == floorNumber).findFirst();
    }

    @Override
    public boolean parkCar(Floor floor, Car car) {
        Floor currFloor = floor;

        currFloor.addParkedCarsPlateNumber(car.getPlateNumber());
        currFloor.setLeftSpots(currFloor.getLeftSpots() - 1);
        currFloor.setLeftWeightCapacity(currFloor.getLeftWeightCapacity() - car.getWeight());

        return selectFloorById(floor.getFloorNumber()).map(fl -> {
            int indexOfFloorToUpdate = parkingHouseFloors.indexOf(fl);
            if (indexOfFloorToUpdate >= 0) {
                parkingHouseFloors.set(indexOfFloorToUpdate, currFloor);
                return true;
            }
            return false;
        })
        .orElse(false);
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
        int amountOfFloors = random.nextInt(2, 10);
        for (int i = 0; i < amountOfFloors; i++) {
            this.addParkingFloor(
                    new Floor(i, random.nextFloat(1.0f, 3.0f), random.nextInt(100000, 1000000), random.nextInt(20, 100)));
        }
        return parkingHouseFloors;
    }
}

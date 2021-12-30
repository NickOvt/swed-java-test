package org.swedtest.swedjavatest.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.swedtest.swedjavatest.dao.CarDao;
import org.swedtest.swedjavatest.dao.ParkingHouseDao;
import org.swedtest.swedjavatest.models.Car;
import org.swedtest.swedjavatest.models.Floor;

@Service
public class CarParkingService {
    @Autowired
    private final CarDao carDao;
    @Autowired
    private final ParkingHouseDao parkingHouseDao;

    public CarParkingService(@Qualifier("fakedao") CarDao carDao,
            @Qualifier("fakeparkinghousedao") ParkingHouseDao parkingHouseDao) {
        this.carDao = carDao;
        this.parkingHouseDao = parkingHouseDao;
    }

    public boolean addCar(Car car) throws Exception {
        return carDao.addCar(car);
    }

    public List<Car> getAllCars() {
        return carDao.getAllCars();
    }

    public List<Floor> initializeParkingHouse() {
        return parkingHouseDao.initializeParkingHouse();
    }

    public boolean parkCar(Car car) throws Exception {
        this.addCar(car);
        List<Floor> floors = parkingHouseDao.getAllFloors();
        int bestSuitableFloorIndex = 0;
        if (floors.size() < 1) {
            throw new Exception("Parking house does not exist yet. Initialize one!");
        }
        for (int i = 1; i < floors.size(); i++) {
            Floor currFloor = floors.get(i);
            Floor prevFloor = floors.get(i - 1);

            if (prevFloor.getCeilingHeight() - car.getHeight() > currFloor.getCeilingHeight() - car.getHeight()
                    && (currFloor.getCeilingHeight() > car.getHeight()
                            && currFloor.getLeftWeightCapacity() > car.getWeight()
                            && currFloor.getLeftSpots() > 0)) {
                bestSuitableFloorIndex = i;
            }
        }
        return parkingHouseDao.parkCar(floors.get(bestSuitableFloorIndex), car);
    }

    public List<Floor> getAllFloors() {
        return parkingHouseDao.getAllFloors();
    }
}

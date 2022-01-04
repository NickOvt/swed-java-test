package org.swedtest.swedjavatest.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

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

    public Optional<Car> getCarByPlateNumber(String plateNumber) {
        return carDao.getCarByPlateNumber(plateNumber);
    }

    public List<Floor> initializeParkingHouse() {
        return parkingHouseDao.initializeParkingHouse();
    }

    public Floor parkCar(Car car) throws Exception {
        List<Floor> floors = parkingHouseDao.getAllFloors();
        int bestSuitableFloorIndex = 0;
        boolean didFindSuitableFloor = false;
        if (floors.size() < 1) {
            throw new Exception(
                    "Parking house does not exist yet. Initialize one! By sending a POST request to /parking/initialize");
        }

        for (int i = 1; i < floors.size(); i++) {
            Floor currFloor = floors.get(i);
            Floor prevFloor = floors.get(i - 1);

            if (prevFloor.getCeilingHeight() - car.getHeight() > currFloor.getCeilingHeight() - car.getHeight()
                    && (currFloor.getCeilingHeight() > car.getHeight()
                            && currFloor.getLeftWeightCapacity() > car.getWeight()
                            && currFloor.getLeftSpots() > 0)) {
                didFindSuitableFloor = true;
                bestSuitableFloorIndex = i;
            }
        }
        if (didFindSuitableFloor) {
            if (!this.addCar(car))
                throw new Exception("Such car already is parked! (Same plate number)");
            return parkingHouseDao.parkCar(floors.get(bestSuitableFloorIndex), car);
        }
        throw new Exception("Did not find a suitable floor to park the car to!");
    }

    public Float unparkCar(Car car) throws Exception {
        Optional<Floor> floorWithCurrentCarToUnpark = parkingHouseDao.selectFloorById(car.getParkedToFloor());
        if (floorWithCurrentCarToUnpark.isEmpty()) {
            throw new Exception("Failed to unpark such a car! Did not find corresponding floor!");
        }
        long diff = new Date().getTime() - car.getParkingTimeStart().getTime();
        Float currentPriceToPay = TimeUnit.MILLISECONDS.toMinutes(diff) * car.getWeight() * 0.001f * car.getHeight()
                * 0.25f;
        
        floorWithCurrentCarToUnpark.get().setLeftSpots(floorWithCurrentCarToUnpark.get().getLeftSpots() + 1);
        floorWithCurrentCarToUnpark.get().setLeftWeightCapacity(-car.getWeight());
        
        parkingHouseDao.unparkCar(floorWithCurrentCarToUnpark.get(), car);
        carDao.deleteCar(car);
        return currentPriceToPay;
    }

    public List<Floor> getAllFloors() {
        return parkingHouseDao.getAllFloors();
    }

    public List<String> getAllCarPlateNumbersFromFloor(int floorId) {
        return parkingHouseDao.getAllCarPlateNumbersFromFloor(floorId);
    }
}

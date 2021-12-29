package org.swedtest.swedjavatest.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.swedtest.swedjavatest.dao.CarDao;
import org.swedtest.swedjavatest.models.Car;

@Service
public class CarParkingService {
    private final CarDao carDao;

    @Autowired
    public CarParkingService(@Qualifier("fakedao") CarDao carDao) {
        this.carDao = carDao;
    }

    public boolean addCar(Car car) throws Exception {
        return carDao.addCar(car);
    }

    public List<Car> getAllCars() {
        return carDao.getAllCars();
    }

    public int parkCar() {
        return 1;
    }
}

package org.swedtest.swedjavatest.services;

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

    public int addCar(Car car) {
        return carDao.addCar(car);
    }
}

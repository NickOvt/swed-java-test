package org.swedtest.swedjavatest.dao;

import java.util.List;
import java.util.Optional;

import org.swedtest.swedjavatest.models.Car;

public interface CarDao {
    boolean addCar(Car car);

    void deleteCar(Car car);

    List<Car> getAllCars();

    Optional<Car> getCarByPlateNumber(String plateNumber);
}

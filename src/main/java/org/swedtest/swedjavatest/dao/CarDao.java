package org.swedtest.swedjavatest.dao;

import java.util.List;

import org.swedtest.swedjavatest.models.Car;

public interface CarDao {
    boolean addCar(Car car) throws Exception;

    List<Car> getAllCars();
}
